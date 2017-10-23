package com.karumi.katagenda.servicelocator

import com.karumi.katagenda.common.repository.InMemoryDataSource
import com.karumi.katagenda.common.repository.Repository
import com.karumi.katagenda.domain.Agenda
import com.karumi.katagenda.domain.Contact
import com.karumi.katagenda.domain.repository.ContactsRepository
import com.karumi.katagenda.ui.ContactsListPresenter
import com.karumi.katagenda.ui.SysOutContactsListView
import com.karumi.katagenda.usecase.AddContact
import com.karumi.katagenda.usecase.GetContacts

object AgendaServiceLocator {

    private val IN_MEMORY_CONTACTS_DATA_SOURCE = InMemoryDataSource<Contact>()

    val contactsListPresenter: ContactsListPresenter
        get() {
            return ContactsListPresenter(sysOutView, getContacts, addContact)
        }

    private val sysOutView: SysOutContactsListView
        get() = SysOutContactsListView()

    private val getContacts: GetContacts
        get() {
            return GetContacts(agenda)
        }

    private val addContact: AddContact
        get() {
            return AddContact(agenda)
        }

    private val agenda: Agenda
        get() {
            return Agenda(contactsRepository)
        }

    private val contactsRepository: Repository<Contact>
        get() = ContactsRepository(IN_MEMORY_CONTACTS_DATA_SOURCE)
}