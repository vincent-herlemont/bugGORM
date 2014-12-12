import buggorm.Obj1
import buggorm.Obj2
import buggorm.TypeObj1

class BootStrap {

    def init = { servletContext ->
        Obj2 obj2 = Obj2.findOrSaveByName("Obj2One")
        Obj2.findOrSaveByName("Obj2Tow")

        TypeObj1 typeObj1 = TypeObj1.findOrSaveByName("TypeObjOne")
        TypeObj1.findOrSaveByName("TypeObjTow")

        Obj1 obj1 = new Obj1(typeObj1: typeObj1,obj2: obj2)
        obj1.save()

        // TODO: BUG GORM
        Obj1.findOrSaveByTypeObj1AndObj2(typeObj1,[obj2])

    }
    def destroy = {
    }
}
