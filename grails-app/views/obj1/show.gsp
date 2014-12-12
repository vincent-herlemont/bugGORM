
<%@ page import="buggorm.Obj1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'obj1.label', default: 'Obj1')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-obj1" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-obj1" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list obj1">
			
				<g:if test="${obj1Instance?.obj2}">
				<li class="fieldcontain">
					<span id="obj2-label" class="property-label"><g:message code="obj1.obj2.label" default="Obj2" /></span>
					
						<g:each in="${obj1Instance.obj2}" var="o">
						<span class="property-value" aria-labelledby="obj2-label"><g:link controller="obj2" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${obj1Instance?.typeObj1}">
				<li class="fieldcontain">
					<span id="typeObj1-label" class="property-label"><g:message code="obj1.typeObj1.label" default="Type Obj1" /></span>
					
						<span class="property-value" aria-labelledby="typeObj1-label"><g:link controller="typeObj1" action="show" id="${obj1Instance?.typeObj1?.id}">${obj1Instance?.typeObj1?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:obj1Instance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${obj1Instance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
