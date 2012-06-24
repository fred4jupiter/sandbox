package org.arquillian.example;

import javax.inject.Inject;

public class Greeter {

    private GreetingTextProvider greetingTextProvider;
    private PhraseBuilder phraseBuilder;

    @Inject
    public Greeter(PhraseBuilder phraseBuilder, GreetingTextProvider greetingTextProvider) {
        this.phraseBuilder = phraseBuilder;
        this.greetingTextProvider = greetingTextProvider;
    }

    public String sayHelloTo(String name) {
        return greetingTextProvider.getGreetingTextByName(name);
    }

    public String sayHelloWithPhraseBuilderTo(String name) {
        return phraseBuilder.buildPhrase("hello", name);
    }

}