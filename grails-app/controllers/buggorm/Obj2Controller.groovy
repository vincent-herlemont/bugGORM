package buggorm



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Obj2Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Obj2.list(params), model:[obj2InstanceCount: Obj2.count()]
    }

    def show(Obj2 obj2Instance) {
        respond obj2Instance
    }

    def create() {
        respond new Obj2(params)
    }

    @Transactional
    def save(Obj2 obj2Instance) {
        if (obj2Instance == null) {
            notFound()
            return
        }

        if (obj2Instance.hasErrors()) {
            respond obj2Instance.errors, view:'create'
            return
        }

        obj2Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'obj2.label', default: 'Obj2'), obj2Instance.id])
                redirect obj2Instance
            }
            '*' { respond obj2Instance, [status: CREATED] }
        }
    }

    def edit(Obj2 obj2Instance) {
        respond obj2Instance
    }

    @Transactional
    def update(Obj2 obj2Instance) {
        if (obj2Instance == null) {
            notFound()
            return
        }

        if (obj2Instance.hasErrors()) {
            respond obj2Instance.errors, view:'edit'
            return
        }

        obj2Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Obj2.label', default: 'Obj2'), obj2Instance.id])
                redirect obj2Instance
            }
            '*'{ respond obj2Instance, [status: OK] }
        }
    }

    @Transactional
    def delete(Obj2 obj2Instance) {

        if (obj2Instance == null) {
            notFound()
            return
        }

        obj2Instance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Obj2.label', default: 'Obj2'), obj2Instance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'obj2.label', default: 'Obj2'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
