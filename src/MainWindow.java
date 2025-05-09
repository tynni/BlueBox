import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class MainWindow {
    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;
    private JLabel title;

    public MainWindow() {
        frame = new JFrame("Blue Box");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        title = new JLabel("Blue Box", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(84f));
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        String[] pages = {"Home","Browse","Rent","Status","Customer","Staff"};
        for (String p : pages) {
            JButton b = new JButton(p);
            b.setPreferredSize(new Dimension(120,40));
            b.addActionListener(e -> showPage(p));
            navBar.add(b);
        }

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.add(createHomePanel(),      "Home");
        cards.add(createBrowsePanel(),    "Browse");
        cards.add(createRentPanel(),      "Rent");
        cards.add(createStatusPanel(),    "Status");
        cards.add(createCustomerPanel(),  "Customer");
        cards.add(createStaffPanel(),     "Staff");

        frame.setLayout(new BorderLayout());
        frame.add(title, BorderLayout.NORTH);
        frame.add(navBar, BorderLayout.SOUTH);
        frame.add(cards, BorderLayout.CENTER);
        frame.setVisible(true);

        showPage("Home");
    }

    private void showPage(String page) {
        cardLayout.show(cards, page);
        title.setVisible(!"Home".equals(page));
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                int w = getWidth(), h = getHeight();

                Color[] blues = {
                        new Color(0x99CCFF),
                        new Color(0x6699CC),
                        new Color(0x336699)
                };

                for (int i = 0; i < blues.length; i++) {
                    int inset = i * 40;
                    int availW = w - 2*inset, availH = h - 2*inset;
                    int side = Math.min(availW, availH);
                    int x = (w - side)/2, y = (h - side)/2;
                    g2.setColor(blues[i]);
                    g2.fillRect(x, y, side, side);
                }
            }
        };

        panel.setBackground(new Color(0x224466));
        panel.setOpaque(true);
        panel.setLayout(new GridBagLayout());
        JLabel homeTitle = new JLabel("Blue Box");
        homeTitle.setFont(homeTitle.getFont().deriveFont(72f));
        homeTitle.setForeground(Color.WHITE);
        homeTitle.setOpaque(false);
        panel.add(homeTitle, new GridBagConstraints());

        return panel;
    }

    private JPanel createBrowsePanel() {
        String[] cols = {"ID","Title","Production","Length","Description","Cast"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        TableColumnModel cm = table.getColumnModel();
        cm.getColumn(0).setMaxWidth(50);
        cm.getColumn(0).setPreferredWidth(30);
        cm.getColumn(4).setCellRenderer(new TextAreaRenderer());
        cm.getColumn(4).setPreferredWidth(300);

        new SwingWorker<List<Movie>,Void>() {
            protected List<Movie> doInBackground() throws Exception {
                return new MovieDAO().getAllMovies();
            }
            protected void done() {
                try {
                    for (Movie m : get()) {
                        model.addRow(new Object[]{
                                m.getMovieID(), m.getTitle(),
                                m.getProduction(), m.getLength(),
                                m.getDescription(), m.getCast()
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        return p;
    }

    private JPanel createRentPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;

        JLabel lblMovie    = new JLabel("Movie ID:");
        JTextField txtMovie    = new JTextField(10);
        JLabel lblCustomer = new JLabel("Customer ID:");
        JTextField txtCustomer = new JTextField(10);
        JLabel lblDuration = new JLabel("Duration (days):");
        JTextField txtDuration = new JTextField(5);
        JLabel lblPrice    = new JLabel("Price:");
        JTextField txtPrice    = new JTextField(7);
        JLabel lblStaff    = new JLabel("Staff ID:");
        JTextField txtStaff    = new JTextField(5);

        c.gridx=0; c.gridy=0; p.add(lblMovie, c);
        c.gridx=1;           p.add(txtMovie, c);
        c.gridx=0; c.gridy=1; p.add(lblCustomer, c);
        c.gridx=1;           p.add(txtCustomer, c);
        c.gridx=0; c.gridy=2; p.add(lblDuration, c);
        c.gridx=1;           p.add(txtDuration, c);
        c.gridx=0; c.gridy=3; p.add(lblPrice, c);
        c.gridx=1;           p.add(txtPrice, c);
        c.gridx=0; c.gridy=4; p.add(lblStaff, c);
        c.gridx=1;           p.add(txtStaff, c);

        JButton btnSubmit = new JButton("Submit Rent");
        c.gridx=0; c.gridy=5; c.gridwidth=2; c.anchor=GridBagConstraints.CENTER;
        p.add(btnSubmit, c);

        btnSubmit.addActionListener(e -> {
            try {
                int movieID    = Integer.parseInt(txtMovie.getText().trim());
                int customerID = Integer.parseInt(txtCustomer.getText().trim());
                int duration   = Integer.parseInt(txtDuration.getText().trim());
                double price   = Double.parseDouble(txtPrice.getText().trim());
                Integer staffID = txtStaff.getText().trim().isEmpty() ? null : Integer.parseInt(txtStaff.getText().trim());

                new SwingWorker<Void,Void>() {
                    protected Void doInBackground() throws Exception {
                        new RentDAO().addRent(new Rent(0, movieID, customerID, duration, price));
                        new StatusDAO().upsertStatus(new Status(movieID, "Rented", staffID));
                        return null;
                    }
                    protected void done() {
                        JOptionPane.showMessageDialog(frame, "Rental recorded!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        txtMovie.setText(""); txtCustomer.setText(""); txtDuration.setText(""); txtPrice.setText(""); txtStaff.setText("");
                    }
                }.execute();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return p;
    }

    private JPanel createStatusPanel() {
        String[] cols = {"MovieID","Status","StaffID"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);

        Runnable reload = () -> {
            model.setRowCount(0);
            try {
                for (Status s : new StatusDAO().getAllStatuses()) {
                    model.addRow(new Object[]{
                            s.getMovieID(),
                            s.getStatus(),
                            s.getStaffID()
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        reload.run();

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;

        JTextField txtMovieID = new JTextField(5);
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Rented","Returned","Overdue"});
        JTextField txtStaffID = new JTextField(5);
        JButton btnSave = new JButton("Save Status");
        JButton btnRefresh = new JButton("Refresh");

        c.gridx=0; c.gridy=0; form.add(new JLabel("Movie ID:"), c);
        c.gridx=1;           form.add(txtMovieID, c);
        c.gridx=0; c.gridy=1; form.add(new JLabel("Status:"), c);
        c.gridx=1;           form.add(cbType, c);
        c.gridx=0; c.gridy=2; form.add(new JLabel("Staff ID:"), c);
        c.gridx=1;           form.add(txtStaffID, c);
        c.gridx=0; c.gridy=3; c.gridwidth=2; c.anchor=GridBagConstraints.CENTER;
        form.add(btnSave, c);

        btnSave.addActionListener(e -> {
            try {
                int mid = Integer.parseInt(txtMovieID.getText().trim());
                String type = (String)cbType.getSelectedItem();
                Integer sid = txtStaffID.getText().trim().isEmpty()
                        ? null
                        : Integer.parseInt(txtStaffID.getText().trim());
                new SwingWorker<Void,Void>() {
                    protected Void doInBackground() throws Exception {
                        new StatusDAO().upsertStatus(new Status(mid, type, sid));
                        return null;
                    }
                    protected void done() {
                        JOptionPane.showMessageDialog(frame,
                                "Status saved!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        txtMovieID.setText("");
                        txtStaffID.setText("");
                        reload.run();
                    }
                }.execute();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid numeric IDs.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRefresh.addActionListener(e -> reload.run());

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(btnRefresh, BorderLayout.EAST);

        JPanel p = new JPanel(new BorderLayout(10,10));
        p.add(scroll, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private JPanel createCustomerPanel() {
        String[] cols = {"CustomerID","Name","Contact"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);

        Runnable reload = () -> {
            model.setRowCount(0);
            try {
                for (Customer c : new CustomerDAO().getAllCustomers()) {
                    model.addRow(new Object[]{ c.getCustomerID(), c.getName(), c.getContact() });
                }
            } catch (Exception e) { e.printStackTrace(); }
        };
        reload.run();

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;
        JTextField txtID = new JTextField(5), txtName = new JTextField(12), txtContact = new JTextField(12);
        txtID.setEditable(true);
        JButton btnAdd = new JButton("Add"), btnUpdate = new JButton("Update"), btnDelete = new JButton("Delete"), btnClear = new JButton("Clear");

        c.gridx=0; c.gridy=0; form.add(new JLabel("ID:"), c);
        c.gridx=1;           form.add(txtID, c);
        c.gridx=0; c.gridy=1; form.add(new JLabel("Name:"), c);
        c.gridx=1;           form.add(txtName, c);
        c.gridx=0; c.gridy=2; form.add(new JLabel("Contact:"), c);
        c.gridx=1;           form.add(txtContact, c);
        JPanel btns = new JPanel(new FlowLayout()); btns.add(btnAdd); btns.add(btnUpdate); btns.add(btnDelete); btns.add(btnClear);
        c.gridx=0; c.gridy=3; c.gridwidth=2; form.add(btns, c);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow()>=0) {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                txtID.setText(model.getValueAt(row,0).toString());
                txtName.setText(model.getValueAt(row,1).toString());
                txtContact.setText(model.getValueAt(row,2).toString());
            }
        });

        btnAdd.addActionListener(e -> {
            String name = txtName.getText().trim(), contact = txtContact.getText().trim();
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new CustomerDAO().addCustomer(new Customer(0,name,contact));
                    return null;}
                protected void done() { reload.run(); txtName.setText(""); txtContact.setText(""); }
            }.execute();
        });
        btnUpdate.addActionListener(e -> {
            int id = Integer.parseInt(txtID.getText().trim());
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new CustomerDAO().updateCustomer(new Customer(id, txtName.getText(), txtContact.getText()));
                    return null;}
                protected void done() { reload.run(); }
            }.execute();
        });
        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(txtID.getText().trim());
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new CustomerDAO().deleteCustomer(id);
                    return null;}
                protected void done() { reload.run(); txtID.setText(""); txtName.setText(""); txtContact.setText(""); }
            }.execute();
        });
        btnClear.addActionListener(e -> { txtID.setText(""); txtName.setText(""); txtContact.setText(""); table.clearSelection(); });

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);

        JPanel p = new JPanel(new BorderLayout(10,10));
        p.add(scroll, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() { setLineWrap(true); setWrapStyleWord(true); setOpaque(true); }
        public Component getTableCellRendererComponent(JTable table, Object val,
                                                       boolean isSel, boolean hasFocus,
                                                       int row, int col) {
            setText(val==null?"":val.toString());
            int h = getPreferredSize().height;
            if (table.getRowHeight(row)!=h) table.setRowHeight(row,h);
            setBackground(isSel ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSel ? table.getSelectionForeground() : table.getForeground());
            return this;
        }
    }

    private JPanel createStaffPanel() {
        String[] cols = {"StaffID","Name"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(table);

        Runnable reload = () -> {
            model.setRowCount(0);
            try {
                for (Staff s : new StaffDAO().getAllStaff()) {
                    model.addRow(new Object[]{ s.getStaffID(), s.getName() });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        reload.run();

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.anchor = GridBagConstraints.WEST;

        JTextField txtID   = new JTextField(5);
        txtID.setEditable(false);
        JTextField txtName = new JTextField(12);
        JButton btnAdd    = new JButton("Add");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear  = new JButton("Clear");

        c.gridx=0; c.gridy=0; form.add(new JLabel("ID:"), c);
        c.gridx=1;           form.add(txtID, c);
        c.gridx=0; c.gridy=1; form.add(new JLabel("Name:"), c);
        c.gridx=1;           form.add(txtName, c);

        JPanel btns = new JPanel(new FlowLayout());
        btns.add(btnAdd); btns.add(btnUpdate); btns.add(btnDelete); btns.add(btnClear);
        c.gridx=0; c.gridy=2; c.gridwidth=2; form.add(btns, c);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow()>=0) {
                int row = table.convertRowIndexToModel(table.getSelectedRow());
                txtID.setText(model.getValueAt(row,0).toString());
                txtName.setText(model.getValueAt(row,1).toString());
            }
        });

        btnAdd.addActionListener(e -> {
            String name = txtName.getText().trim();
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new StaffDAO().addStaff(new Staff(0,name));
                    return null;
                }
                protected void done() { reload.run(); txtName.setText(""); }
            }.execute();
        });
        btnUpdate.addActionListener(e -> {
            int id = Integer.parseInt(txtID.getText().trim());
            String name = txtName.getText().trim();
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new StaffDAO().updateStaff(new Staff(id,name));
                    return null;
                }
                protected void done() { reload.run(); }
            }.execute();
        });
        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(txtID.getText().trim());
            new SwingWorker<Void,Void>() {
                protected Void doInBackground() throws Exception {
                    new StaffDAO().deleteStaff(id);
                    return null;
                }
                protected void done() { reload.run(); txtID.setText(""); txtName.setText(""); }
            }.execute();
        });
        btnClear.addActionListener(e -> {
            txtID.setText(""); txtName.setText(""); table.clearSelection();
        });

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);

        JPanel p = new JPanel(new BorderLayout(10,10));
        p.add(scroll, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
