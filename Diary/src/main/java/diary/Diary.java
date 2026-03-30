package diary;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public class Diary extends JPanel  {
    private static final String TITLE = "Ежедневник";
    private static final int WIDTH = 1000, HEIGHT = 500;
    private JTextField textUrl;
    private JButton button, create, buttonHttp, buttonRemove;
    private JLabel label;
    private String token = UUID.randomUUID().toString();
    public void DiaryMethod(JFrame frame) {
        int width = 300;
        int height = frame.getHeight();
        int x = (frame.getWidth() - width) / 2;
        int y = (frame.getHeight() - height) / 2;
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        frame.add(this);
        // Календарь
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        this.add(datePanel);

        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        JList<String> jList = new JList<>(defaultListModel);
        JScrollPane jScrollPane = new JScrollPane(jList);
        jScrollPane.setBackground(Color.blue);
        frame.add(jScrollPane);
        label = new JLabel("google map, park");
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Arial", Font.PLAIN, 26));
        this.add(label);
        textUrl = new JTextField();
        textUrl.setFont(new Font("Arial", Font.PLAIN, 26));
        this.add(textUrl);
        button = new JButton("добавить заметку");
        button.setBounds(285, 325,500,45);
        button.setFocusable(false);
        buttonHttp = new JButton("browser");
        buttonHttp.setBounds(0, 385, 500,25);
        buttonHttp.setFocusable(false);
        buttonRemove = new JButton("удалить заметку");
        buttonRemove.setBounds(285, 370,500,45);
        buttonRemove.setFocusable(false);
        frame.add(buttonRemove);
        frame.add(button);

        create = new JButton("добавить");
        create.setBounds(0,410, this.getWidth(), 45);
        create.setFocusable(false);
        this.add(create);
        textUrl.setBounds(0,250, this.getWidth(), 45);
        label.setBounds(0,340, this.getWidth(),45);
        jScrollPane.setBounds(0,0, frame.getWidth(),250);
        datePanel.setBounds(0,0, this.getWidth(),165);
        buttonRemove.addActionListener( e -> {
            try {
                new RemoveContent();
                SwingUtilities.invokeLater(() -> {
                    int index = defaultListModel.size() - 1;
                    if(index >= 0) {
                        defaultListModel.remove(index);
                    }
                });
            } catch (Exception exception) {
                new IOException("Error status");
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                open("Путь к браузеру.");
            }
        });
        button.addActionListener(e -> {
             this.setVisible(true);
             button.setVisible(false);
             buttonRemove.setVisible(false);
        });
        create.addActionListener(e -> {
           this.setVisible(false);
           button.setVisible(true);
           buttonRemove.setVisible(true);
           new Thread(() -> {
               try {
                   defaultListModel.addElement(new PostExample().saveContent(model.getValue() + ": "+ textUrl.getText()));
                   textUrl.setText("");
               } catch (Exception exception) {
                   System.out.println(exception.getMessage());
               }
           }).start();
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    try {
                        new Tokens(token);
                        SwingUtilities.invokeLater(() -> {
                            List<String> cons = null;
                            try {
                                cons = new CreatesContents().getContent(token);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            for (String const_content : cons) {
                                defaultListModel.addElement(const_content);
                            }
                        });
                    } catch (Exception exception) {
                        System.out.println("Error connected: " + exception.getMessage());
                    }
                }).start();
            }
            @Override
            public void windowClosing(WindowEvent e) {
            System.exit(0);
            }
        });
        this.setLayout(null);
        this.setVisible(false);
        frame.setLayout(null);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 26));
        g.setColor(Color.BLUE);
        g.drawString("Заметки", 0, 240);
        g.setFont(new Font("Arial", Font.PLAIN, 26));
        g.setColor(Color.BLUE);
        g.drawString("URL", 0, 330);
    }
    public static void open(String url) {
        // Метод open, сделано (ИИ)
        try {
            // 1. Сначала пробуем Desktop
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(new URI(url));
                    return; // Всё успешно, выходим
                }
            }

            // 2. Определяем ОС и вызываем системную команду
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();

            if (os.contains("linux")) {
                // Попробуем Chrome, если задан BROWSER
                String browser = System.getenv("BROWSER");
                if (browser != null && !browser.isEmpty()) {
                    rt.exec(new String[]{browser, url});
                } else {
                    // fallback на xdg-open
                    rt.exec(new String[]{"xdg-open", url});
                }
            } else if (os.contains("mac")) {
                rt.exec(new String[]{"open", url});
            } else if (os.contains("win")) {
                rt.exec(new String[]{"cmd", "/c", "start", url});
            } else {
                System.err.println("Неизвестная ОС: " + os + ". Откройте ссылку вручную: " + url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Не удалось открыть URL: " + url);
        }
    }
    public static void main(String[] args) throws JsonProcessingException {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        new Diary().DiaryMethod(frame);
        frame.setVisible(true);
    }
}