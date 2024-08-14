package ui;

import studentinfo.Course;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;

public class Sis {
    static final int WIDTH = 350;
    static final int HEIGHT = 400;
    private CoursesPanel panel;
    static final String COURSES_TITLE = "Course Listing";
    private final JFrame frame = new JFrame(COURSES_TITLE);

    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
    }

    private void initialize() {
        createCoursePanel();
        createKeyListeners();

        ImageIcon imageIcon = ImageUtil.create("images/courses.gif");
        frame.setIconImage(imageIcon.getImage());

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    JFrame getFrame() {
        return frame;
    }

    void close() {
        frame.dispose();
    }

    void createCoursePanel() {
        panel = new CoursesPanel();
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
        panel.addRemoveButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = panel.getSelectedCourse();
                if (selectedCourse != null) {
                    panel.removeCourse(selectedCourse);
                } else {
                    JOptionPane.showMessageDialog(frame, "No course selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addCourse() {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Course course;
                try {
                    course = new Course(panel.getDepartment(), panel.getNumber(), panel.getDate());
                } catch (Exception e) {
                    course = new Course(panel.getDepartment(), panel.getNumber());
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                panel.addCourse(course);
                setAddButtonState();
                return null;
            }

            @Override
            protected void done() {
                frame.setCursor(Cursor.getDefaultCursor());
            }
        };

        worker.execute();
    }

    private void removeCourse() {
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Course selectedCourse = panel.getSelectedCourse();
                if (selectedCourse != null) {
                    panel.removeCourse(selectedCourse);
                }
                return null;
            }

            @Override
            protected void done() {
                setRemoveButtonState();
                frame.setCursor(Cursor.getDefaultCursor());
            }
        };

        worker.execute();
    }


    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setButtonState();
            }
        };
        panel.addFieldListener(FieldCatalog.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(FieldCatalog.NUMBER_FIELD_NAME, listener);
        setButtonState();
    }

    void setButtonState() {
        setAddButtonState();
        setRemoveButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME,
                verifyFilterField(FieldCatalog.DEPARTMENT_FIELD_NAME, FieldCatalog.NUMBER_FIELD_NAME));
    }

    void setRemoveButtonState() {
        panel.setEnabled(CoursesPanel.REMOVE_BUTTON_NAME, panel.getSelectedCourse() != null);
    }

    private boolean verifyFilterField(String... fields) {
        for (String fieldName : fields) {
            JTextField field = panel.getField(fieldName);

            if (isEmptyField(field))
                return false;
            if (!verifyFilter(field))
                return false;
        }
        return true;
    }

    private boolean isEmptyField(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private boolean verifyFilter(JTextField field) {
        AbstractDocument document = (AbstractDocument) field.getDocument();
        ChainableFilter currentFilter = (ChainableFilter) document.getDocumentFilter();

        while (currentFilter != null) {
            String textField = field.getText();
            if (!currentFilter.verify(textField))
                return false;

            if (currentFilter instanceof ChainableFilter chainableFilter)
                currentFilter = (ChainableFilter) chainableFilter.getNextFilter();
            else
                break;
        }
        return true;
    }
}