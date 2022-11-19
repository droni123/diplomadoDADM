package mx.com.idel.diplomado.model.repository

import mx.com.idel.diplomado.model.entities.UserModel

class UserRepository {
    companion object{
         fun getUserList() : ArrayList<UserModel>{
            val data = arrayListOf<UserModel>()
            val url = "https://img2.freepng.es/20180623/iqh/kisspng-computer-icons-avatar-social-media-blog-font-aweso-avatar-icon-5b2e99c40ce333.6524068515297806760528.jpg"
            data.add(UserModel("Idelfonso 1","Apellido 1",32,url))
            data.add(UserModel("Eileen","Apellido 2",32,url))
            data.add(UserModel("Itzel","Apellido 3",30,url))
            data.add(UserModel("Elvira","Apellido 4",20,url))
            data.add(UserModel("De la Cruz","Apellido 5",25,url))
            data.add(UserModel("Hernández","Apellido 6",15,url))
            data.add(UserModel("Soto","Apellido 7",18,url))
            data.add(UserModel("Bermidez","Apellido 8",43,url))
            data.add(UserModel("Otro Nombre","Apellido 9",24,url))
            data.add(UserModel("Yesenia","Apellido 10",35,url))
            data.add(UserModel("Narciso","Apellido 11",34,url))
            data.add(UserModel("Maria","Apellido 12",36,url))
            data.add(UserModel("Francisca","Apellido 13",27,url))
            data.add(UserModel("Valdéz","Apellido 14",50,url))
            data.add(UserModel("Márquez","Apellido 15",24,url))
            data.add(UserModel("Pancho","Apellido 16",23,url))
            data.add(UserModel("Juan","Apellido 17",24,url))
            data.add(UserModel("Antonio","Apellido 18",24,url))
            return data
        }
    }
}