<%@page import="java.io.PrintWriter"%>
<%@page language="java" session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="span6">
	<div class="dataTables_info" id="sample_1_info">
		<c:if test="${count == 0}">
			Showing ${indexAndCount[0]} to ${part2} of ${count} entries	
		</c:if>
		<c:if test="${count > 0}">
			Showing ${indexAndCount[0] + 1} to ${part2} of ${count} entries	
		</c:if>
		
	</div>
</div>
<div class="span6">
	<div class="dataTables_paginate paging_bootstrap pagination">
		<ul>
			<c:choose>
				<c:when test="${activePage==1}">
					<li class="prev disabled">
						<a href="#">← Prev</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="prev">
						<a href="#" id="linkPagePrev" onClick="handleGoToPage(event, '${activePage-1}')">← Prev</a>
					</li>
				</c:otherwise>
			</c:choose>	
				<%
					Integer lenNumPage = (Integer) request.getAttribute("numpage");
					Integer activePage = (Integer) request.getAttribute("activePage");
					PrintWriter outs = response.getWriter();	
					for (int i=1;i<=lenNumPage.intValue();i++){						
						if (i == activePage){ %>
							<li class='active'>		
				<% 		} else { %>
							<li>
				<% 		}  %>
						<a href='#' onClick='handleGoToPage(event,"<%=i%>")'><%=i%></a></li>	
				<% 	}
					
					if (activePage >= lenNumPage){ %>
						<li class='next disabled'><a href='#'>Next → </a></li>	
				<% 	} else { %>
						<li class='next'><a href='#' onClick='handleGoToPage(event, "${activePage+1}")'>Next → </a></li>
				<% 	}
				%>
				
		</ul>
		
	</div>
</div>

<form id="pagingForm" method="post" action="/sscnServer/verifikasi.do">
	<input type="hidden" name="activePage" id="activePage"/>
	<input type="hidden" name="numPage" id="numPage"/>
	<input type="hidden" name="searchPage" id="searchPage"/>
</form>
