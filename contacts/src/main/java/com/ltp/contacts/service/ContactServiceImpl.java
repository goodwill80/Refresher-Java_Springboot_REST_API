package com.ltp.contacts.service;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;

@Service
// @ConditionalOnProperty(name ="server.port", havingValue = "8080")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    

    private int findIndexById(String id) {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow();
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    } 

    @Override
    public Contact getContactById(String id) {
        return contactRepository.getContact(String.valueOf(findIndexById(id)));
    }

}
