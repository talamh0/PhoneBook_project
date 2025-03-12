import tkinter as tk
from tkinter import messagebox

class PhoneBookGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("PhoneBook")
        self.root.geometry("450x500")
        self.root.configure(bg="#FAE3E3")  

        self.contacts = {}

    
        title_label = tk.Label(root, text="PhoneBook", font=("Arial", 18, "bold"), bg="#FAE3E3", fg="#333")
        title_label.pack(pady=10)

    
        input_frame = tk.Frame(root, bg="#FFFFFF", padx=10, pady=10, relief="ridge", bd=2)
        input_frame.pack(pady=5, padx=10, fill="both")

        tk.Label(input_frame, text="Name:", font=("Arial", 12), bg="#FFFFFF").grid(row=0, column=0, sticky="w", padx=5, pady=3)
        self.name_entry = tk.Entry(input_frame, width=30, font=("Arial", 12), bg="#E3F2FD") 
        self.name_entry.grid(row=0, column=1, padx=5, pady=3)

        tk.Label(input_frame, text="Phone:", font=("Arial", 12), bg="#FFFFFF").grid(row=1, column=0, sticky="w", padx=5, pady=3)
        self.phone_entry = tk.Entry(input_frame, width=30, font=("Arial", 12), bg="#E3F2FD") 
        self.phone_entry.grid(row=1, column=1, padx=5, pady=3)


        button_frame = tk.Frame(root, bg="#FAE3E3")
        button_frame.pack(pady=5)

        add_button = tk.Button(button_frame, text="Add Contact", font=("Arial", 12), bg="#A8E6CF", fg="black", width=15, command=self.add_contact)  # أخضر فاتح
        add_button.grid(row=0, column=0, padx=5, pady=5)

        search_button = tk.Button(button_frame, text="Search Contact", font=("Arial", 12), bg="#A8E6CF", fg="black", width=15, command=self.search_contact)
        search_button.grid(row=0, column=1, padx=5, pady=5)

        delete_button = tk.Button(button_frame, text="Delete Contact", font=("Arial", 12), bg="#FF8A80", fg="black", width=15, command=self.delete_contact)  # أحمر فاتح
        delete_button.grid(row=1, column=0, padx=5, pady=5)

        show_button = tk.Button(button_frame, text="Show All Contacts", font=("Arial", 12), bg="#FF8A80", fg="black", width=15, command=self.show_contacts)
        show_button.grid(row=1, column=1, padx=5, pady=5)

     
        result_frame = tk.Frame(root, bg="#FFFFFF", padx=10, pady=5, relief="ridge", bd=2)
        result_frame.pack(pady=5, padx=10, fill="both")

        result_label = tk.Label(result_frame, text="Contacts:", font=("Arial", 14, "bold"), bg="#FFFFFF", fg="#333")
        result_label.pack()

        self.result_text = tk.Text(result_frame, width=50, height=8, font=("Arial", 12), bg="#FFF5E1", fg="black", relief="ridge", bd=2)  # وردي بيج فاتح
        self.result_text.pack()
        self.result_text.config(state="disabled")  

    def add_contact(self):
        name = self.name_entry.get().strip()
        phone = self.phone_entry.get().strip()
        if name and phone:
            self.contacts[name] = phone
            messagebox.showinfo("Success", f"{name} added successfully!")
            self.clear_entries()
        else:
            messagebox.showwarning("Error", "Please enter both Name and Phone.")

    def search_contact(self):
        name = self.name_entry.get().strip()
        if name in self.contacts:
            self.update_result(f"{name}: {self.contacts[name]}")
        else:
            messagebox.showinfo("Not Found", f"{name} is not in contacts.")

    def delete_contact(self):
        name = self.name_entry.get().strip()
        if name in self.contacts:
            del self.contacts[name]
            messagebox.showinfo("Deleted", f"{name} has been removed.")
            self.clear_entries()
        else:
            messagebox.showinfo("Not Found", f"{name} is not in contacts.")

    def show_contacts(self):
        if not self.contacts:
            self.update_result("No contacts available.")
        else:
            result_text = "\n".join([f"{name}: {phone}" for name, phone in self.contacts.items()])
            self.update_result(result_text)

    def update_result(self, text):
        self.result_text.config(state="normal")
        self.result_text.delete(1.0, tk.END)
        self.result_text.insert(tk.END, text)
        self.result_text.config(state="disabled")

    def clear_entries(self):
        self.name_entry.delete(0, tk.END)
        self.phone_entry.delete(0, tk.END)


root = tk.Tk()
app = PhoneBookGUI(root)
root.mainloop()
