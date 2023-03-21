package bookopedia.logic.commands;

import static bookopedia.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookopedia.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookopedia.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static bookopedia.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import bookopedia.commons.core.index.Index;
import bookopedia.model.AddressBook;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.Model;
import bookopedia.model.ModelManager;
import bookopedia.model.UserPrefs;
import bookopedia.model.person.Person;
import bookopedia.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkCommand.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markOtw_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.OTW;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markDone_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.DONE;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markFailed_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.FAILED;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark)
                .withDeliveryStatus(deliveryStatus)
                .withNoOfDeliveryAttempts(1)
                .build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPending_success() {
        DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPersonToMark = new PersonBuilder(personToMark).withDeliveryStatus(deliveryStatus).build();

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, deliveryStatus);
        String expectedMessage = String.format(MarkCommand.MESSAGE_SUCCESS, personToMark.getName(), deliveryStatus);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()), updatedPersonToMark);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, DeliveryStatus.OTW);

        assertCommandFailure(markCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
