package buggorm



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TypeObj1Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TypeObj1.list(params), model:[typeObj1InstanceCount: TypeObj1.count()]
    }

    def show(TypeObj1 typeObj1Instance) {
        respond typeObj1Instance
    }

    def create() {
        respond new TypeObj1(params)
    }

    @Transactional
    def save(TypeObj1 typeObj1Instance) {
        if (typeObj1Instance == null) {
            notFound()
            return
        }

        if (typeObj1Instance.hasErrors()) {
            respond typeObj1Instance.errors, view:'create'
            return
        }

        typeObj1Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'typeObj1.label', default: 'TypeObj1'), typeObj1Instance.id])
                redirect typeObj1Instance
            }
            '*' { respond typeObj1Instance, [status: CREATED] }
        }
    }

    def edit(TypeObj1 typeObj1Instance) {
        respond typeObj1Instance
    }

    @Transactional
    def update(TypeObj1 typeObj1Instance) {
        if (typeObj1Instance == null) {
            notFound()
            return
        }

        if (typeObj1Instance.hasErrors()) {
            respond typeObj1Instance.errors, view:'edit'
            return
        }

        typeObj1Instance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TypeObj1.label', default: 'TypeObj1'), typeObj1Instance.id])
                redirect typeObj1Instance
            }
            '*'{ respond typeObj1Instance, [status: OK] }
        }
    }

    @Transactional
    def delete(TypeObj1 typeObj1Instance) {

        if (typeObj1Instance == null) {
            notFound()
            return
        }

        typeObj1Instance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TypeObj1.label', default: 'TypeObj1'), typeObj1Instance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'typeObj1.label', default: 'TypeObj1'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
