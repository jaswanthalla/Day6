import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoApp {

    private JFrame frame;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ToDoApp().createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Elevate Labs To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Tasks"));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        taskInput = new JTextField();
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new AddTaskListener());
        deleteButton.addActionListener(new DeleteTaskListener());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskInput.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class DeleteTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}