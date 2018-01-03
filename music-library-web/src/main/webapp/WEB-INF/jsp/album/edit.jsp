<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${album.title}">
<jsp:attribute name="pageHeader">
	<div class="page-header">
		<div class="pull-right">
			<my:a href="/album/detail/${album.id}" class="btn btn-default">
				<fmt:message key="albums.edit.backToDetail"/>
			</my:a>
		</div>
		<h1>
			<my:a href="/album/list"><fmt:message key="albums.edit.albums"/></my:a> /
			<c:out value="${album.title}"/>
		</h1>
	</div>
</jsp:attribute>
	<jsp:attribute name="body">
		<form:form method="POST" action="${pageContext.request.contextPath}/album/edit/${album.id}"
				   modelAttribute="album" cssClass="form-horizontal"
				   enctype="multipart/form-data">
			<div class="form-group ${title_error ? 'has-error' : ''}">
				<form:label path="title" cssClass="col-sm-2 control-label">
					<fmt:message key="albums.edit.album.title"/>
				</form:label>
				<div class="col-sm-10">
					<form:input path="title" cssClass="form-control"/>
					<form:errors path="title" cssClass="help-block"/>
				</div>
			</div>
			<div class="form-group ${releaseDate_error ? 'has-error' : ''}">
				<form:label path="releaseDate" cssClass="col-sm-2 control-label">
					<fmt:message key="albums.edit.album.releaseDate"/>
				</form:label>
				<div class="col-sm-10">
					<fmt:message key="albums.edit.album.releaseDate.placeholder" var="placeholder"/>
					<form:input path="releaseDate" placeholder="${placeholder}" cssClass="form-control"/>
					<form:errors path="releaseDate" cssClass="help-block"/>
				</div>
			</div>
			<div class="form-group ${albumArt_error ? 'has-error' : ''}">
				<form:label path="albumArt" cssClass="col-sm-2 control-label">
					<fmt:message key="albums.edit.album.albumArt"/>
				</form:label>
				<div class="col-sm-10">
					<form:input path="albumArt" type="file"/>
					<form:errors path="albumArt" cssClass="help-block"/>
				</div>
			</div>
			<div class="form-group ${commentary_error ? 'has-error' : ''}">
				<form:label path="commentary" cssClass="col-sm-2 control-label">
					<fmt:message key="albums.edit.album.commentary"/>
				</form:label>
				<div class="col-sm-10">
					<form:input path="commentary" cssClass="form-control"/>
					<form:errors path="commentary" cssClass="help-block"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-primary" type="submit">
						<fmt:message key="albums.edit.save"/>
					</button>
				</div>
			</div>
		</form:form>
	</jsp:attribute>
</my:pagetemplate>
