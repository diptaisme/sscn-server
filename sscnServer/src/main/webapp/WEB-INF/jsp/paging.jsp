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
		&nbsp; Goto Page &nbsp;<input type="text" aria-controls="example" id="inpGoTo" value="${activePage}" style="width: 20px">&nbsp;<a href="#" class="btn" style="width:20px" onClick="goToPage(event, '${numpage}')">Go</a>
											
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
					int x = (activePage/2) * 2;
					if (lenNumPage < (x + 10)){
						x = lenNumPage - 10; 
					} else if (lenNumPage == (x+10)){
						x = x-1;
					}
					int j = 0;
					if (x < 0){
						x = 0;
						j = lenNumPage;
					} else {
						j = 10;	
					}
					for (int i=1;i<=j;i++){
						
						if ((x+i) == activePage){ %>
							<li class='active'>		
				<% 		} else { %>
							<li>
				<% 		}  %>
						<a href='#' onClick='handleGoToPage(event,"<%=x+i%>")'><%=x+i%></a></li>	
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

