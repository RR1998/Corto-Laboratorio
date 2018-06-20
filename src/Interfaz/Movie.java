/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

/**
 *
 * @author LN710Q
 */

    import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Movie extends JFrame {
    public JLabel lblID, lblNombre, lblPais, lblDirector, lblAño, lblClasificacion, lblExibicion;
    public JTextField ID, nombre, Pais , Director, Año, Exibicion;
    public JComboBox Clasificacion;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 175, ALTOC = 20;

    DefaultTableModel tm;

    public Movie() {
        super("Peliculas baratas a dos coras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        
        //AGREGANDO TODOS LOS COMPONENTES A LA PANTALLA
        //LABELS
        container.add(lblID);
        container.add(lblNombre);
        container.add(lblPais);
        container.add(lblDirector);
        container.add(lblClasificacion);
        container.add(lblExibicion);
        //JTEXTFIELDS
        container.add(ID);
        container.add(Clasificacion);
        container.add(Director);
        container.add(nombre);
        container.add(Pais);
        container.add(Exibicion);
        
        //CHECKBOX
        container.add(si);
        container.add(no);
        //JBUTTONS
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        
        setSize(900, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblID = new JLabel("N° Pelicula");
        lblNombre = new JLabel("Nombre");
        lblPais = new JLabel("Pais");
        lblDirector = new JLabel("Director");
        lblClasificacion=new JLabel("Clasificacion");
        lblAño = new JLabel("Año lanzamiento");
        lblExibicion=new JLabel("Estado");

        lblID.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblDirector.setBounds(400, 60, ANCHOC, ALTOC);
        lblClasificacion.setBounds(10,140, ANCHOC, ALTOC);
        lblExibicion.setBounds(10, 180, ANCHOC, ALTOC);
        lblAño.setBounds(10, 240, ANCHOC, ALTOC);
    }

    public final void formulario() {
        //CREACION DE TEXT FIELDS
        ID = new JTextField();
        Clasificacion = new JComboBox();
        Pais = new JTextField();
        nombre=new JTextField();
        Director= new JTextField();
        Exibicion = new JTextField();
        
        si = new JRadioButton("Exibiendose", true);
        no = new JRadioButton("Fuera de sala");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        Clasificacion.addItem("G");
        Clasificacion.addItem("PG");
        Clasificacion.addItem("14A");
        Clasificacion.addItem("18A");
        Clasificacion.addItem("R");
        Clasificacion.addItem("A");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        //TAMAÑO Y LUGAR DE LOS TEXTFIELDS
        ID.setBounds(140, 10, ANCHOC, ALTOC);
        Clasificacion.setBounds(140,140,ANCHOC,ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        Director.setBounds(500, 60, ANCHOC, ALTOC);
        Pais.setBounds(140, 100, 80, ALTOC);
        Exibicion.setBounds(140, 100, 100, ALTOC);
        si.setBounds(140, 180, 50, ALTOC);
        no.setBounds(210, 180, 50, ALTOC);

        //TAMAÑO Y LUGAR DE LOS BOTONES 
        buscar.setBounds(350, 10, 100, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        //JTABLE
        resultados = new JTable();
        table.setBounds(100, 250, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("N° Pelicula");
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyeccion");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getId() ,fi.getNombre(), fi.getDirector(), fi.getPaisProcedencia(), fi.getClasificacion(), fi.getAñoEstreno(), fi.isExibicion()});
        }
        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                
                Filtro    f=new Filtro(Integer.parseInt(ID.getText()), nombre.getText(), Integer.parseInt(Año.getText()), Director.getText(), Pais.getText(), Clasificacion.getSelectedItem().toString(), true);
                if (no.isSelected()) {
                    f.setExibicion(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro    f=new Filtro(nombre.getText(), Integer.parseInt(Pais.getText()), Director.getText(), ID.getText(), Clasificacion.getSelectedItem().toString(), true);
                if (no.isSelected()) {
                    f.setExibicion(false);
                }
                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(ID.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(ID.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro buscado no se ha encontrado");
                } else {
                    ID.setText(f.getNombre());
                    Clasificacion.setSelectedItem(f.getDirector());
                    Pais.setText(Integer.toString(f.getAñoEstreno()));

                    if (f.isExibicion()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarCampos();
            }
        });
    }
    
    public void limpiarCampos() {
        ID.setText("");
        Clasificacion.setSelectedItem("");
        Pais.setText("");
    }
    
    public static void main(String [] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Movie().setVisible(true);
            }
        });
    }
    
    
}
