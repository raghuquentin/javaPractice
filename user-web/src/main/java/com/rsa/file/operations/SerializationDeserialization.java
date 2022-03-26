package com.rsa.file.operations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
class User implements Serializable{
    private static final Long SerialVersionUID=123459872343L;
    private String userName;
    private Long userId;
    private LocalDate dob;
    static Double salary = 1000d;
    private transient Integer age;

}
public class SerializationDeserialization {
    public static void main(String[] args) {
        User u = new User("Neruppuda",1001l,LocalDate.now(),30);
        User.salary=2000d;
        String file = "C:/Users/aragh/Downloads/ocuments/test.txt";
        try(FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
            System.out.println("Serialization start");
            objectOutputStream.writeObject(u);
            System.out.println("Serialization end");
        }catch (IOException e)
        {
        e.printStackTrace();
        }
        try(FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println("Deserializing start....");
            User user = (User) objectInputStream.readObject();
            System.out.println(user.getUserName());
            System.out.println(user.getUserId());
            System.out.println(user.getDob());
            System.out.println(user.getAge());

        }catch (IOException  | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
