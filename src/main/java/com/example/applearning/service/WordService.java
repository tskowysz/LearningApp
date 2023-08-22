package com.example.applearning.service;

import com.example.applearning.repository.WordRepo;
import com.example.applearning.repository.Words;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WordService {

    WordRepo wordRepo;
    Random random;

    List<Words>wordsList;

    public WordService(WordRepo wordRepo, Random random) {
        this.wordRepo = wordRepo;
        this.random = random;
        this.wordsList=new ArrayList<>();
    }

    public List<Words>findAllFromRepo(){
        wordsList = wordRepo.findAll().stream().collect(Collectors.toList());
        return wordsList;
    }

    public Words losuj(List<Words> wordsList) {
        Words words = wordsList.get(random.nextInt(wordsList.size()));
        return words;
    }

    public Optional<Words> findById(Long id) {
        return Optional.of(wordRepo.getReferenceById(id));
    }

    public Stream<Words> sortByCategory(String category) {
        Stream<Words> wordsStream = wordRepo.findAll().stream().filter(word -> word.getCategory() == category);
        return wordsStream;
    }

    public Set<String> findByCategory(List<Words>wordsList) {
        Stream<String> stringStream = wordsList.stream().map(x -> x.getCategory());
        Set<String> collect = stringStream.collect(Collectors.toSet());
        return collect;
    }

}
