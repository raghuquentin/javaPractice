package com.rsa.file.operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * class is implemented by Serializable interface, so this class objects can be serializable
 * if you not implement by Serializable interface and trying to serialize , it throws NotSerializableException
 **/
class User implements Serializable{
    private static final Long SerialVersionUID=123459872343L;
    private String userName;
    private Long userId;
    private LocalDate dob;
    //we cannot serialize static field
    static Double salary = 1000d;
    private transient Integer age;
    private transient boolean isMale;

}
public class SerializationDeserialization {
    public static void main(String[] args) {
        User u = new User("Neruppuda",1001l,LocalDate.now(),30,true);
        User.salary=2000d;
        String file = "C:/Users/aragh/Downloads/Documents/test.txt";
        //create fileoutputstream to write by using file path
        try(FileOutputStream outputStream = new FileOutputStream(file);
            //for serialize we need to use ObjectOutPutStream, it takes stream as input
            //passing fileOutputStream as arg to serialize the object in file
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            System.out.println("Serialization start");
            //serialize the object to the file (object to byte stream.)
            objectOutputStream.writeObject(u);
            System.out.println("Serialization end");
        }catch (IOException e)
        {
        e.printStackTrace();
        }
        //to deserialize we create fileInputstream to read the file, which is used to serialize
        try(FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println("Deserializing start....");
            //deserialize the object (byte -> original object)
            User user = (User) objectInputStream.readObject();
            System.out.println(user.getUserName());
            System.out.println(user.getUserId());
            System.out.println(user.getDob());
            /*
            * if we serialize transient field, it return null if it is refernce type
            * it return 0 or 0.0 or false if it is primitive type
             */
            System.out.println(user.getAge());
            System.out.println(user.isMale());

        }catch (IOException  | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
