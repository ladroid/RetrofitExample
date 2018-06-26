# RetrofitExample
This is example with Retrofit

Hello everyone. This project works with Retrofit.

How I did it? At first you should add in **gradle.build** this things

```xml
compile 'com.squareup.retrofit2:retrofit:2.3.0'
compile 'com.squareup.retrofit2:converter-gson:2.3.0'
```

In Retrofit OkHttp installed automatically. 
The next is using Retrofit. I made server and I have json file it calls **db.json**

After we must to connect and get all info from ther.

How to do it?

1) Make your own api

2) make a class which get all thing from json

3) connection

Let's do it! At first own api.

This is part of code.

```java
public interface MessageAPI {
    @GET("employees/")
    Call<List<Message>> messages();

    @GET("employees/{id}")
    Call<Message> getUserById(@Path("id") Integer id);

    @GET("employees/")
    Call<Message> message();

    @GET("employees/")
    Call<JsonElement> json();
}
```

As you can see we have interface with some methods. Also for this all methods I added annotation. 
Retrofit has some annotation like: **GET**, **POST**, **HEAD**, **Path**, **PUT** and others. But this annotations are very popular.

Okay, let's go back in my code, I have this ```@GET("employees/") ```. But what is it employees. Well go to my json file and you will see it. In webbrowser it looks like ```http://LOCAL_HOST:3000/employees```.

The next is make a class which get all thing from json.

This is my code:

```java
public class Message {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstName;
    @SerializedName("lastname")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("title")
    @Expose
    private String title;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }
}
```
The question is why I added annotation, I mean ```@SerializedName("something")``` and ```@Expose```. Well I added it because if you want for example to get info from id then you should do this, add annotations.

And then is connection.

This is part of my code:

```java
public void startingId() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessageAPI messageAPI = retrofit.create(MessageAPI.class);

        Id = Integer.parseInt(enter.getText().toString());

        Call<Message> messageCall = messageAPI.getUserById(Id);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message post = response.body();
                if(response.isSuccessful()) {
                    output.setText(post.getFirstName() + " " + post.getEmail());
                }
                else
                {
                    Log.d("CODE1", "CODE IS " + response.code());
                    Toast.makeText(MainActivity.this, "CODE IS " +
                            String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("FAIL1", "Fail" + t);
                Toast.makeText(MainActivity.this, "FAIL",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
```

Here as you can see at first I added this

```java
 Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```
By this line of code I can connect to my server and then convert JSON to normal view.
The next code is to get and to work with my data that's why I added this part of code

```java
MessageAPI messageAPI = retrofit.create(MessageAPI.class);
Call<Message> messageCall = messageAPI.getUserById(Id);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message post = response.body();
                if(response.isSuccessful()) {
                    output.setText(post.getFirstName() + " " + post.getEmail());
                }
                else
                {
                    Log.d("CODE1", "CODE IS " + response.code());
                    Toast.makeText(MainActivity.this, "CODE IS " +
                            String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("FAIL1", "Fail" + t);
                Toast.makeText(MainActivity.this, "FAIL",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
```

**enqueue(...)** means that everything will be asynchronous. So it will work asynchronous. But if you want that all your "transactions" work synchronous just change **enqueue(...)** on **execute(...)**.

I yhink that's all. Maybe sometimes I will improve my code and this short doc. Thanks.
