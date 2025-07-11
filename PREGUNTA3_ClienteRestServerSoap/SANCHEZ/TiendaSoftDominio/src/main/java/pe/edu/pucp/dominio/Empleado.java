package pe.edu.pucp.dominio;

public class Empleado extends Persona {
    private String cargo;
    private Double salario;
    private String departamento;
    private Empleado supervisor;
    
    public Empleado() {
        super();
    }
    
    public Empleado(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public Empleado(String nombre, String apellido, String dni, 
                   String cargo, Double salario, String departamento) {
        super(nombre, apellido, dni);
        this.cargo = cargo;
        this.salario = salario;
        this.departamento = departamento;
    }
    
    // Getters y Setters
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    
    public Empleado getSupervisor() { return supervisor; }
    public void setSupervisor(Empleado supervisor) { this.supervisor = supervisor; }
    
    @Override
    public String toString() {
        return "Empleado{" + super.toString() + 
               ", cargo=" + cargo + 
               ", salario=" + salario + 
               ", departamento=" + departamento + 
               ", supervisor=" + (supervisor != null ? supervisor.getId() : "null") + '}';
    }
}