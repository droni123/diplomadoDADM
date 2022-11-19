package mx.com.idel.diplomado.model.repository

import mx.com.idel.diplomado.model.entities.UserModel

class UserRepository {
    companion object{
         fun getUserList() : ArrayList<UserModel>{
            var data = arrayListOf<UserModel>()
            data.add(UserModel("Idelfonso 1","Apellido 1",32))
            data.add(UserModel("Eileen","Apellido 2",32))
            data.add(UserModel("Itzel","Apellido 3",30))
            data.add(UserModel("Elvira","Apellido 4",20))
            data.add(UserModel("De la Cruz","Apellido 5",25))
            data.add(UserModel("Hernández","Apellido 6",15))
            data.add(UserModel("Soto","Apellido 7",18))
            data.add(UserModel("Bermidez","Apellido 8",43))
            data.add(UserModel("Otro Nombre","Apellido 9",24))
            data.add(UserModel("Yesenia","Apellido 10",35))
            data.add(UserModel("Narciso","Apellido 11",34))
            data.add(UserModel("Maria","Apellido 12",36))
            data.add(UserModel("Francisca","Apellido 13",27))
            data.add(UserModel("Valdéz","Apellido 14",50))
            data.add(UserModel("Márquez","Apellido 15",24))
            data.add(UserModel("Pancho","Apellido 16",23))
            data.add(UserModel("Juan","Apellido 17",24))
            data.add(UserModel("Antonio","Apellido 18",24))
            return data
        }
    }
}