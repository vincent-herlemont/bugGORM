<%@ page import="buggorm.Obj1" %>



<div class="fieldcontain ${hasErrors(bean: obj1Instance, field: 'obj2', 'error')} ">
	<label for="obj2">
		<g:message code="obj1.obj2.label" default="Obj2" />
		
	</label>
	<g:select name="obj2" from="${buggorm.Obj2.list()}" multiple="multiple" optionKey="id" size="5" value="${obj1Instance?.obj2*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: obj1Instance, field: 'typeObj1', 'error')} required">
	<label for="typeObj1">
		<g:message code="obj1.typeObj1.label" default="Type Obj1" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="typeObj1" name="typeObj1.id" from="${buggorm.TypeObj1.list()}" optionKey="id" required="" value="${obj1Instance?.typeObj1?.id}" class="many-to-one"/>

</div>

