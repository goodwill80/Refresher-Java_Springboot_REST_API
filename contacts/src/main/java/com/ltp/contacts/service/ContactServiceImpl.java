package com.ltp.contacts.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.ltp.contacts.exception.NoContactException;
import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;

@Service
// @ConditionalOnProperty(name ="server.port", havingValue = "8080")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    

    // Stream the length of Array then find the contact based on the id
    private int findIndexById(String id) throws NoContactException {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow(()-> new NoContactException());
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.saveContact(contact);
    } 

    @Override
    public void updateContact(String id, Contact contact) throws NoContactException {
        contactRepository.updateContact(this.findIndexById(id), contact);
    }

    @Override
    public void deleteContact(String id) throws NoContactException {
        contactRepository.deleteContact(this.findIndexById(id));
    }

    @Override
    public Contact getContactById(String id) throws NoContactException  {
        return contactRepository.getContact(String.valueOf(findIndexById(id)));
    }

}
