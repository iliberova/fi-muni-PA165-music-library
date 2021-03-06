<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message key="navigation.albums" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

	<p>
		<my:a href="/album/create" class="btn btn-success">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			<fmt:message key="albumCreate.title"/>
		</my:a>
	</p>

	<table class="table table-align-middle table-striped">
		<thead>
			<tr>
				<th class="col-xs-1 col-md-1 col-lg-1"><fmt:message key="album.id"/></th>
				<th><fmt:message key="album.name"/></th>
				<th colspan="2" class="col-xs-2 col-md-3 col-lg-2"><fmt:message key="album.actions"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${albums}" var="album">
				<tr>
					<td>${album.id}</td>
					<td><my:a href="/album/detail/${album.id}"><c:out value="${album.title}"/></my:a></td>
					<td>
						<my:a href="/album/edit/${album.id}" class="btn btn-default">
							<fmt:message key="album.edit"/>
						</my:a>
					</td>
					<td>
						<form method="post" action="${pageContext.request.contextPath}/album/delete/${album.id}">
							<button type="submit" class="btn btn-danger"><fmt:message key="album.delete"/></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</jsp:attribute>
</my:pagetemplate>
