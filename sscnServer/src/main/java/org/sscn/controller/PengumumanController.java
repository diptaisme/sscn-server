package org.sscn.controller;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.sscn.dao.DtPengumumanDao;
import org.sscn.dao.DtUserDao;
import org.sscn.persistence.entities.DtPengumuman;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.PengumumanService;
import org.sscn.services.UserService;

@Controller
public class PengumumanController implements HandlerExceptionResolver {

	@Inject
	private PengumumanService pengumumanService;
	
	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/cb_instansi.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<RefInstansi>> getInstansis() {
		List<RefInstansi> instansis = pengumumanService.getInstansi(null);
		Map<String, List<RefInstansi>> instansiMap = new HashMap<String, List<RefInstansi>>();
		instansiMap.put("instansis", instansis);
		return instansiMap;
	}

	/**
	 * @param model
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/pengumuman.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		List<DtUser> users = userService.getAllUser(null);
		model.addAttribute("users", users);

		FileUpload form = new FileUpload();
		model.addAttribute("fileUploadCommand", form);

		return "pengumuman";
	}

	/**
	 * @param form
	 * @param result
	 * @param modelMap
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(HttpSession session,
	        @ModelAttribute(value = "fileUploadCommand") FileUpload form,
	        BindingResult result, ModelMap modelMap) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			modelMap.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		if (!result.hasErrors()) {
			String mimeTypeString = form.getFile().getFileItem().getContentType();
			if (mimeTypeString.equals("application/pdf")) {

				// Dummy RefInstansi
				RefInstansi refInstansi = new RefInstansi();
				refInstansi.setKode("4011");
				DtPengumuman instance = new DtPengumuman(refInstansi, form.getFile()
				        .getBytes(), "1");
				// Cari RefInstansi, apakah sudah ada atau belum
				List<DtPengumuman> dtPengumumans = pengumumanService.findByProperty("refInstansi.kode", "4011", null);
				// Cek jika laporan dengan instansi yg sama telah ada maka
				// update.
				if (dtPengumumans.size() >= 1) {
					DtPengumuman updateInstanceDtPengumuman = dtPengumumans.get(0);
					updateInstanceDtPengumuman.setBerita(form.getFile().getBytes());
					pengumumanService.updatePengumuman(updateInstanceDtPengumuman);
				} else {
					pengumumanService.insertPengumuman(instance);
				}

				FileUpload tmpFileUpload = (FileUpload) modelMap.get("fileUploadCommand");
				modelMap.addAttribute("statusUpload", "File <b>"
				        + tmpFileUpload.getFile().getOriginalFilename()
				        + "</b> berhasil di upload ("
				        + getBeritaSize(tmpFileUpload.getFile().getSize()) + ")");
			} else {
				modelMap.put("errors", "Maaf, ekstensi file harus pdf.");
			}
			return "pengumuman";
		} else {
			return "pengumuman";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerExceptionResolver#resolveException
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
	        HttpServletResponse arg1, Object arg2, Exception exception) {
		Map<Object, Object> model = new HashMap<Object, Object>();

		if (exception instanceof MaxUploadSizeExceededException) {
			model.put("errors", "Besar file tidak boleh melebihi "
			        + ((MaxUploadSizeExceededException) exception).getMaxUploadSize()
			        + " byte.");
		} else {
			exception.printStackTrace();
			model.put("errors", "Unexpected error: " + exception.getMessage());
		}
		model.put("fileUploadCommand", new FileUpload());
		return new ModelAndView("/pengumuman", (Map) model);
	}

	/**
	 * @param size
	 * @return besar dokumen dalam (Byte/KB/MB)
	 */
	private String getBeritaSize(Long size) {
		final Formatter formatter = new Formatter();
		final Integer intSize = size.intValue();
		String result = "";
		final Double tmp;
		if (intSize < 1024) {
			result = intSize.toString() + " Byte";
		} else if ((intSize >= 1024) && ((intSize / 1024) < 1024)) {
			tmp = new Double(intSize) / new Double(1024.0);
			result = formatter.format("%1.2f", tmp).toString() + " KB";
		} else if (intSize >= (1024 * 1024)) {
			tmp = new Double(intSize) / (new Double(1024.0) * new Double(1024.0));
			result = formatter.format("%1.2f", tmp).toString() + " MB";
		}
		formatter.close();
		return result;
	}

}
