package buggorm


import grails.test.mixin.*
import spock.lang.*

@TestFor(Obj1Controller)
@Mock(Obj1)
class Obj1ControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.obj1InstanceList
        model.obj1InstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.obj1Instance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def obj1 = new Obj1()
        obj1.validate()
        controller.save(obj1)

        then: "The create view is rendered again with the correct model"
        model.obj1Instance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        obj1 = new Obj1(params)

        controller.save(obj1)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/obj1/show/1'
        controller.flash.message != null
        Obj1.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def obj1 = new Obj1(params)
        controller.show(obj1)

        then: "A model is populated containing the domain instance"
        model.obj1Instance == obj1
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def obj1 = new Obj1(params)
        controller.edit(obj1)

        then: "A model is populated containing the domain instance"
        model.obj1Instance == obj1
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/obj1/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def obj1 = new Obj1()
        obj1.validate()
        controller.update(obj1)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.obj1Instance == obj1

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        obj1 = new Obj1(params).save(flush: true)
        controller.update(obj1)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/obj1/show/$obj1.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/obj1/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def obj1 = new Obj1(params).save(flush: true)

        then: "It exists"
        Obj1.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(obj1)

        then: "The instance is deleted"
        Obj1.count() == 0
        response.redirectedUrl == '/obj1/index'
        flash.message != null
    }
}
