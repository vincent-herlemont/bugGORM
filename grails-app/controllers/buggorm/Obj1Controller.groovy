package buggorm



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Obj1Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Obj1.list(params), model:[obj1InstanceCount: Obj1.count()]
    }

    def show(Obj1 obj1Instance) {
        respond obj1Instance
    }

    def create() {
        respond new Obj1(params)
    }

    @Transactional
    def save(Obj1 obj1Instance) {
        if (obj1Instance == null) {
            notFound()
            return
        }

        if (obj1Instance.hasErrors()) {
            respond obj1Instance.errors, view:'create'
            return
        }

        obj1Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'obj1.label', default: 'Obj1'), obj1Instance.id])
                redirect obj1Instance
            }
            '*' { respond obj1Instance, [status: CREATED] }
        }
    }

    def edit(Obj1 obj1Instance) {
        respond obj1Instance
    }

    @Transactional
    def update(Obj1 obj1Instance) {
        if (obj1Instance == null) {
            notFound()
            return
        }

        if (obj1Instance.hasErrors()) {
            respond obj1Instance.errors, view:'edit'
            return
        }

        obj1Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Obj1.label', default: 'Obj1'), obj1Instance.id])
                redirect obj1Instance
            }
            '*'{ respond obj1Instance, [status: OK] }
        }
    }

    @Transactional
    def delete(Obj1 obj1Instance) {

        if (obj1Instance == null) {
            notFound()
            return
        }

        obj1Instance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Obj1.label', default: 'Obj1'), obj1Instance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'obj1.label', default: 'Obj1'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
