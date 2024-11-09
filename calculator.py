import tkinter as tk
from tkinter import messagebox

# Function to update expression in the text entry
def press(num):
    current = entry.get()
    entry.delete(0, tk.END)
    entry.insert(tk.END, current + str(num))

# Function to evaluate the final expression
def equal():
    try:
        result = str(eval(entry.get()))
        entry.delete(0, tk.END)
        entry.insert(tk.END, result)
    except Exception as e:
        messagebox.showerror("Error", "Invalid Input")

# Function to clear the entry
def clear():
    entry.delete(0, tk.END)

# Function to delete the last character
def delete_last():
    current = entry.get()
    entry.delete(0, tk.END)
    entry.insert(tk.END, current[:-1])

# Setting up the main window
root = tk.Tk()
root.title("Calculator")

# Entry widget for input and result display
entry = tk.Entry(root, width=16, font=('Arial', 20), borderwidth=2, relief='ridge')
entry.grid(row=0, column=0, columnspan=4)

# Button layout
buttons = [
    ('%', 1, 0), ('CE', 1, 1), ('C', 1, 2), ('⌫', 1, 3),
    ('1/x', 2, 0), ('x²', 2, 1), ('√', 2, 2), ('÷', 2, 3),
    ('7', 3, 0), ('8', 3, 1), ('9', 3, 2), ('*', 3, 3),
    ('4', 4, 0), ('5', 4, 1), ('6', 4, 2), ('-', 4, 3),
    ('1', 5, 0), ('2', 5, 1), ('3', 5, 2), ('+', 5, 3),
    ('+/-', 6, 0), ('0', 6, 1), ('.', 6, 2), ('=', 6, 3),
]

# Dictionary to map button text to functions
for (text, row, col) in buttons:
    if text == '=':
        btn = tk.Button(root, text=text, width=5, height=2, font=('Arial', 15), command=equal)
    elif text == 'C':
        btn = tk.Button(root, text=text, width=5, height=2, font=('Arial', 15), command=clear)
    elif text == '⌫':
        btn = tk.Button(root, text=text, width=5, height=2, font=('Arial', 15), command=delete_last)
    elif text == '+/-':
        btn = tk.Button(root, text=text, width=5, height=2, font=('Arial', 15), command=lambda: press('-' if entry.get() == '' else ''))
    else:
        btn = tk.Button(root, text=text, width=5, height=2, font=('Arial', 15), command=lambda txt=text: press(txt if txt not in ['1/x', 'x²', '√', '÷'] else ('/' if txt == '÷' else ('**2' if txt == 'x²' else ('**0.5' if txt == '√' else '')))))
    btn.grid(row=row, column=col)

root.mainloop()
