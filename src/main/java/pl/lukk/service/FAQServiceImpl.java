package pl.lukk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lukk.repository.FAQRepository;

@Service
public class FAQServiceImpl
{
    @Autowired
    private FAQRepository faqRepo;

}
