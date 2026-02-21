package presentacion;

import dominio.Gasto;
import dominio.ResumenGastos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import negocio.ControladorNegocio;

/**
 * Ventana principal de la aplicación Calculadora de Gastos.
 * Implementa una interfaz Swing para el registro de gastos y visualización de resúmenes.
 * * Equipo de Desarrollo:
 * - Ari Raul Montoya Navarro
 * - Jorge Cuevas Gastelum
 * - Camila Zubía Higuera
 * - Isaias Coronado
 * - Saul Isaac Apodaca Baldenegro
 */
public class VentanaPrincipal extends JFrame {

    private JTextField txtMonto;
    private JButton btnAgregarGasto, btnGenerarResumen;
    private JTextArea taResumen;
    private ControladorNegocio controlador;
    private JComboBox<String> comboCategorias;

    /**
     * Constructor de la Ventana Principal.
     * Configura la interfaz gráfica, inicializa componentes y establece los manejadores de eventos.
     */
    public VentanaPrincipal() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        controlador = new ControladorNegocio();
        setTitle("Calculadora de Gastos");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 10));
        panelFormulario.setBorder(new TitledBorder("Registro de Gasto"));
        txtMonto = new JTextField();
        comboCategorias = new JComboBox<>();
        comboCategorias.setEditable(true);
        JTextField editor = (JTextField) comboCategorias.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_ESCAPE
                        && evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN) {
                    if (comboCategorias.getItemCount() > 0) {
                        comboCategorias.showPopup();
                    }
                }
            }
        });
        panelFormulario.add(new JLabel("Monto ($):"));
        panelFormulario.add(txtMonto);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(comboCategorias);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAgregarGasto = new JButton("Agregar Gasto");
        btnAgregarGasto.setBackground(Color.BLACK);
        btnAgregarGasto.setForeground(Color.WHITE);
        btnAgregarGasto.setFont(btnAgregarGasto.getFont().deriveFont(Font.BOLD));
        btnAgregarGasto.setOpaque(true);
        btnAgregarGasto.setBorderPainted(false);
        btnGenerarResumen = new JButton("Generar Resumen");
        btnGenerarResumen.setBackground(Color.WHITE);
        btnGenerarResumen.setForeground(Color.BLACK);
        btnGenerarResumen.setFont(btnGenerarResumen.getFont().deriveFont(Font.BOLD));
        btnGenerarResumen.setOpaque(true);
        btnGenerarResumen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBotones.add(btnAgregarGasto);
        panelBotones.add(btnGenerarResumen);
        taResumen = new JTextArea();
        taResumen.setEditable(false);
        taResumen.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taResumen.setBackground(new Color(245, 245, 245));
        JScrollPane scrollResumen = new JScrollPane(taResumen);
        scrollResumen.setBorder(new TitledBorder("Vista de Resumen"));
        JPanel panelNorte = new JPanel(new BorderLayout(5, 5));
        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelPrincipal.add(scrollResumen, BorderLayout.CENTER);
        add(panelPrincipal);
        btnAgregarGasto.addActionListener(e -> agregarGasto());
        btnGenerarResumen.addActionListener(e -> generarResumen());
    }

    /**
     * Método invocado al presionar "Agregar Gasto".
     * Lee los campos, valida la entrada, crea la categoría si es nueva y registra el gasto.
     */
    private void agregarGasto() {
        try {
            String montoStr = txtMonto.getText().trim();
            Object item = comboCategorias.getEditor().getItem();
            String catNombre = (item != null) ? item.toString().trim().toUpperCase() : "";
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (catNombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La categoría es obligatoria.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!controlador.verificarCategoria(catNombre)) {
                controlador.agregarCategoria(catNombre);
                actualizarSugerencias();
            }
            Gasto gasto = new Gasto(monto, catNombre, java.time.LocalDate.now());
            controlador.agregarGasto(gasto);
            JOptionPane.showMessageDialog(this, "Gasto agregado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido. Ingrese solo números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualiza la lista desplegable de categorías con los datos más recientes del controlador.
     * Mantiene el texto que el usuario estaba escribiendo.
     */
    private void actualizarSugerencias() {
        Object actual = comboCategorias.getEditor().getItem();
        comboCategorias.removeAllItems();
        for (String nombre : controlador.obtenerNombresCategorias()) {
            comboCategorias.addItem(nombre);
        }
        comboCategorias.getEditor().setItem(actual);
    }

    /**
     * Método invocado al presionar "Generar Resumen".
     * Obtiene los datos de gastos, formatea un reporte y lo muestra en el JTextArea.
     */
    private void generarResumen() {
        controlador.generarResumen(); // Imprime en consola (opcional)
        ResumenGastos resumen = controlador.obtenerResumen();
        taResumen.setText("");
        taResumen.append("===================================\n");
        taResumen.append("         RESUMEN DE GASTOS         \n");
        taResumen.append("===================================\n");       
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        taResumen.append("Generado el: " + dtf.format(LocalDateTime.now()) + "\n\n");
        taResumen.append(String.format("%-20s   %10s\n", "CATEGORÍA", "MONTO"));
        taResumen.append("-----------------------------------\n");
        for (Map.Entry<String, Double> entry : resumen.getGastosPorCategoria().entrySet()) {
            String linea = String.format("%-20s $ %10.2f\n", entry.getKey(), entry.getValue());
            taResumen.append(linea);
        }
        taResumen.append("-----------------------------------\n");
        String totalLinea = String.format("%-20s $ %10.2f\n", "TOTAL ACUMULADO:", resumen.getTotalGastos());
        taResumen.append(totalLinea);
        taResumen.append("===================================\n");
    }

    /**
     * Limpia los campos de entrada de texto de la interfaz.
     */
    private void limpiarCampos() {
        txtMonto.setText("");
        comboCategorias.getEditor().setItem("");
    }

    /**
     * Punto de entrada principal de la aplicación.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}