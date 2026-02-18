package presentacion;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import dominio.Gasto;
import dominio.ResumenGastos;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import negocio.ControladorNegocio;

public class VentanaPrincipal extends JFrame {
    private JTextField txtMonto, txtCategoria, txtDescripcion;
    private JButton btnAgregarGasto, btnGenerarResumen;
    private JTextArea taResumen;
    private ControladorNegocio controlador;

    // Constructor
    public VentanaPrincipal() {
        controlador = new ControladorNegocio(); // Crear el controlador

        // Configurar la ventana
        setTitle("Calculadora de Gastos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes de la interfaz
        JLabel lblMonto = new JLabel("Monto:");
        txtMonto = new JTextField(15);
        
        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField(15);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField(15);
        
        btnAgregarGasto = new JButton("Agregar Gasto");
        btnGenerarResumen = new JButton("Generar Resumen");
        
        taResumen = new JTextArea(10, 30);
        taResumen.setEditable(false);

        // Agregar componentes a la ventana
        add(lblMonto);
        add(txtMonto);
        add(lblCategoria);
        add(txtCategoria);
        add(lblDescripcion);
        add(txtDescripcion);
        add(btnAgregarGasto);
        add(btnGenerarResumen);
        add(new JScrollPane(taResumen));

        // Eventos
        btnAgregarGasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarGasto();
            }
        });

        btnGenerarResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarResumen();
            }
        });
    }

    // Método para agregar un gasto
    private void agregarGasto() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            String categoria = txtCategoria.getText();
            String descripcion = txtDescripcion.getText();
            Gasto gasto = new Gasto(monto, categoria, java.time.LocalDate.now(), descripcion);
            controlador.agregarGasto(gasto);
            JOptionPane.showMessageDialog(this, "Gasto agregado exitosamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un monto válido.");
        }
    }

    // Método para generar el resumen de los gastos
    private void generarResumen() {
        controlador.generarResumen();  // Generar el resumen
        ResumenGastos resumen = controlador.obtenerResumen();  // Obtener el resumen calculado
        taResumen.setText("");  // Limpiar el área de texto
        taResumen.append("Total de Gastos: $" + resumen.getTotalGastos() + "\n\n");
        for (Map.Entry<String, Double> entry : resumen.getGastosPorCategoria().entrySet()) {
            taResumen.append(entry.getKey() + ": $" + entry.getValue() + "\n");
        }
    }


    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtMonto.setText("");
        txtCategoria.setText("");
        txtDescripcion.setText("");
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}