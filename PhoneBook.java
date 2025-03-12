import java.util.Scanner;

public class PhoneBook {
	
	private static Scanner scanner = new Scanner(System.in);
	private static BST<Contact> contactsList = new BST<Contact>();
	private static Event eventsArray[] = new Event[50];
	
	// Method to add new contact
	public static void addContact() {	
		System.out.print("Enter the contact's name:");
		String name = scanner.nextLine();
		
		System.out.print("Enter the contact's phone number:");
		String phone = scanner.nextLine();
		
		System.out.print("Enter the contact's email address:");
		String email = scanner.nextLine();
		
		System.out.print("Enter the contact's address:");
		String address = scanner.nextLine();
		
		System.out.print("Enter the contact's birthday:");
		String birthday = scanner.nextLine();
		
		System.out.print("Enter any notes for the contact:");
		String notes = scanner.nextLine();
		
		Contact contact = new Contact(name, phone, email, address, birthday, notes);
		
		// Each contact in the phone book should be unique
		if (contactsList.find(contact)) {
			System.out.println("Contact already exists!");
			return;
		}
		
		contactsList.insert(contact);			
		System.out.println("Contact added successfully!");
	}
	
	// Method to search for a contact
	public static void searchContact() {		
		System.out.println("Enter search criteria:");
		System.out.println("1. Name");
		System.out.print("Enter your choice:");
	
		String option = scanner.nextLine();
		
		if (option.equals("1")) {
			System.out.print("Enter the contact's name:");			
			String name = scanner.nextLine();
			Contact contact = new Contact(name);
			if (contactsList.find(contact)) {
				System.out.println("Contact found!");
				System.out.println(contactsList.retrieve());
			} else {
				System.out.println("Contact not found!");
			}
		}
	}

	// Method to delete a contact
	public static void deleteContact() {
		System.out.print("Enter the contact's name:");
		String name = scanner.nextLine();
		Contact contact = new Contact(name);
		
		// When a contact is deleted all events with that contact are also deleted
		if (contactsList.find(contact)) {
			removeContactsEvents(contactsList.retrieve());
			contactsList.remove();
			System.out.println("Contact deleted successfully!");
		} else {
			System.out.println("Contact not found!");
		}
	}

	// Method to remove all events of a contact
	private static void removeContactsEvents(Contact contact) {
		Event tempArray[] = new Event[eventsArray.length];
		
		for (int i = 0; i < eventsArray.length; i++) {
			if (eventsArray[i] != null && eventsArray[i].find(contact)) {
				if (eventsArray[i].type.equals("event")) {
					String contactsName = eventsArray[i].contacts.replace(contact.getName(), "").trim();						
					if (contactsName.equals("")) {
						eventsArray[i] = null;
					} else {
						if (contactsName.charAt(0) == ',')
							contactsName = contactsName.substring(1).trim();
						eventsArray[i].contacts = contactsName.trim();	
					}
				} else {
					eventsArray[i] = null;
				}
			}
		}
		
		for (int i = 0, j = 0; i < eventsArray.length; i++) {
			if (eventsArray[i] != null)
				tempArray[j++] = eventsArray[i];
		}
		
		eventsArray = tempArray;
	}

	// Method to add new event
	public static void scheduleEvent() {
		System.out.println();
		System.out.println("Enter type:");
		System.out.println("1. event");
		System.out.println("2. appointment");
		System.out.println();
		System.out.print("Enter your choice:");
	
		String option = scanner.nextLine();
		String type = "event";
		
		if (option.equals("2")) {
			type = "appointment";
		}		
		
		System.out.print("Enter " + type + " title:");
		String title = scanner.nextLine();
		
		System.out.print("Enter contacts name separated by a comma:");
		String contactsName = scanner.nextLine();
		
		System.out.print("Enter " + type + " date and time (MM/DD/YYYY HH:MM):");
		String time = scanner.nextLine();
		
		System.out.print("Enter " + type + " location:");
		String location = scanner.nextLine();
		
		if (!type.equals("event") && contactsName.contains(",")) {
			System.out.println("Can not add more than one contact to appointment.");
			return;
		}
		
		LinkedList<Contact> contacts = new LinkedList<Contact>(); 

		for (String contactName : contactsName.split(",")) {
			Contact contact = new Contact(contactName.trim());
			
			if (!contactsList.find(contact)) {
				System.out.println("Contact not found!" + contact.lastName);
				return;
			}
			
			contact = contactsList.retrieve();			
			
			// Check Conflict
			for (int i = 0; i < eventsArray.length; i++) {
				if (eventsArray[i] != null) {
					if (eventsArray[i].find(contact) && eventsArray[i].time.equals(time)) {
						System.out.println("Event conflict with another event of contact.");
						return;
					}
				}
			}
			
			contacts.insert(contact);
		}
		
		Event event = new Event(title, time, location, type, contactsName);
		
		Event tempArray[] = new Event[eventsArray.length];

		int idx = 0;
		for (int i = 0; i < eventsArray.length; i++) {
			if (eventsArray[i] != null && eventsArray[i].compareTo(event) <= 0)
				tempArray[idx++] = eventsArray[i];
			else
				break;
		}
		
		tempArray[idx] = event;
		
		for (int i = idx; i < eventsArray.length - 1; i++) {
			tempArray[i+1] = eventsArray[i];
		}	
		
		eventsArray = tempArray;
		
		System.out.println(type + " scheduled successfully!");
	}
	
	// Method to print event details
	public static void printEvent() {		
		System.out.println();
		System.out.println("Enter search criteria:");
		System.out.println("1. contact name");
		System.out.println("2. Event tittle");
		System.out.println();
		System.out.print("Enter your choice:");
	
		String option = scanner.nextLine();
		
		if (option.equals("1")) {
			System.out.print("Enter the contact's name:");
			String contactName = scanner.nextLine();
			Contact contact = new Contact(contactName);
			String list = "";
			for (int i = 0; i < eventsArray.length; i++) {
				if (eventsArray[i] != null && eventsArray[i].find(contact)) {
					list += eventsArray[i];
				}
			}
			if (list != "") {
				System.out.println("Events found!");
				System.out.println(list);
			} else {
				System.out.println("Event not found!");
			}
		} else if (option.equals("2")) {
			System.out.print("Enter the event title:");
			String title = scanner.nextLine();
			String list = "";
			for (int i = 0; i < eventsArray.length; i++) {
				if (eventsArray[i] != null && eventsArray[i].title.equals(title)) {
					list += eventsArray[i];
				}
			}
			if (list != "") {
				System.out.println("Events found!");
				System.out.println(list);
			} else {
				System.out.println("Event not found!");
			}
		}
	}

	// Method to all contacts with same first name
	public static void printByFirstName() {	
		System.out.print("Enter the first name:");
		String firstName = scanner.nextLine();
		String list = contactsList.getContactsByFirstName(firstName);

		if (list != "") {
			System.out.println("Contacts found!");
			System.out.println(list);
		} else {
			System.out.println("Contacts not found!");
		}
	}

	// Method to print all events alphabetically
	public static void printAllEvents() {
		String list = "";
		for (int i = 0; i < eventsArray.length; i++) {
			if (eventsArray[i] != null)
				list += eventsArray[i] + "\n";
			else
				break;
		}
		if (!list.equals("")) {
			System.out.println("Events found!");
			System.out.println(list);
		} else {
			System.out.println("Event not found!");
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Linked Tree Phonebook!");

		String option;
		do {
			System.out.print("Please choose an option:\n"
			+ "1. Add a contact\n"
			+ "2. Search for a contact\n"
			+ "3. Delete a contact\n"
			+ "4. Schedule an event/appointment\n"
			+ "5. Print event details\n"
			+ "6. Print contacts by first name\n"
			+ "7. Print all events alphabetically\n"
			+ "8. Exit\n"
			+ "Enter your choice:");

			option = scanner.nextLine();
			
			if (option.equals("1"))
				addContact();
			else if (option.equals("2"))
				searchContact();
			else if (option.equals("3"))
				deleteContact();
			else if (option.equals("4"))
				scheduleEvent();
			else if (option.equals("5"))
				printEvent();
			else if (option.equals("6"))
				printByFirstName();
			else if (option.equals("7"))
				printAllEvents();
			else if (option.equals("8"))
				System.out.println("Goodbye!");

			System.out.println();
		} while (!option.equals("8"));

		scanner.close();
	}
}
