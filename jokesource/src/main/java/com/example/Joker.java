package com.example;

public class Joker {
    String jokes[]={"Some people just have a way with words, and other people … oh … not have way",
    "The four most beautiful words in our common language:told you so",
            "All pro athletes are bilingual. They speak English and profanity",
            "I don’t want to brag, but I do speak pig Latin; I mean,I’m not fluent, but I’m sure if I ever went there, I could get by"
    ,"A family of mice were surprised by a big cat. Father Mouse jumped and and said, \\\"Bow-wow!\\\" The cat ran away. \\\"What was that, Father?\\\" asked Baby Mouse. \\\"Well, son, that's why it's important to learn a second language"
    ,"Patient: Doctor, I have a pain in my eye whenever I drink tea. \n" +
            "Doctor: Take the spoon out of the mug before you drink. "};

    public String getJoke() {
       return jokes[(int)(Math.random() * jokes.length)];
      //  return "This is totally a funny joke";
    }

}
