package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Representa la página de registro del sistema bajo prueba.
 * Hereda de BasePage para reutilizar métodos comunes de interacción con elementos.
 */
public class RegistroPage extends BasePage{

    // Localizadores de elementos de la página
    private By nombre= By.id("customer.firstName");
    private By apellido= By.id("customer.lastName");
    private By direccion= By.id("customer.address.street");
    private By ciudad= By.id("customer.address.city");
    private By estado= By.id("customer.address.state");
    private By codigoPostal= By.id("customer.address.zipCode");
    private By celular= By.id("customer.phoneNumber");
    private By ssn= By.id("customer.ssn");
    private By usuario= By.id("customer.username");
    private By contraseña= By.id("customer.password");
    private By recontraseña= By.id("repeatedPassword");
    private By btnRegistrarse= By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    private By mensajeBienvenida= By.xpath("//*[@id=\"rightPanel\"]/h1");
    private By mensajeRegistro= By.xpath("//*[@id=\"rightPanel\"]/p");

    private By mensajeNombreRequerido= By.id("customer.firstName.errors");
    private By mensajeApellidoRequerido= By.id("customer.lastName.errors");
    private By mensajeDireccionRequerido= By.id("customer.address.street.errors");
    private By mensajeCiudadRequerido= By.id("customer.address.city.errors");
    private By mensajeEstadoRequerido= By.id("customer.address.state.errors");
    private By mensajeCodigoPostalRequerido= By.id("customer.address.zipCode.errors");
    private By mensajeSsnRequerido= By.id("customer.ssn.errors");
    private By mensajeUsuarioRequerido= By.id("customer.username.errors");
    private By mensajeContraseñaRequerido= By.id("customer.password.errors");
    private By mensajeRecontraseñaRequerido= By.id("repeatedPassword.errors");

    /**
     * Constructor de la página de login.
     * @param driver instancia del WebDriver utilizada para interactuar con el navegador.
     * @param wait instancia de WebDriverWait (no se usa directamente, ya que la clase base lo crea).
     */
    public RegistroPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**
     * Escribe el nombre en el campo correspondiente.
     * @param name el nombre de registro a ingresar.
     */
    public void escribirNombre(String name) {
        this.sendText(name, nombre);
    }

    /**
     * Escribe el apellido en el campo correspondiente.
     * @param lastname el apellido de registro a ingresar.
     */
    public void escribirApellido(String lastname) {
        this.sendText(lastname, apellido);
    }

    /**
     * Escribe la direccion en el campo correspondiente.
     * @param address la direccio de registro a ingresar.
     */
    public void escribirDireccion(String address) {
        this.sendText(address, direccion);
    }

    /**
     * Escribe la ciudad en el campo correspondiente.
     * @param city la ciudad de registro a ingresar.
     */
    public void escribirCiudad(String city) {
        this.sendText(city, ciudad);
    }

    /**
     * Escribe el estado en el campo correspondiente.
     * @param state el estado de registro a ingresar.
     */
    public void escribirEstado(String state) {
        this.sendText(state, estado);
    }

    /**
     * Escribe el codigo postal en el campo correspondiente.
     * @param zipCode el codigo postal de registro a ingresar.
     */
    public void escribirCodigoPostal(String zipCode) {
        this.sendText(zipCode, codigoPostal);
    }

    /**
     * Escribe el celular en el campo correspondiente.
     * @param phone el celular de registro a ingresar.
     */
    public void escribirCelular(String phone) {
        this.sendText(phone, celular);
    }

    /**
     * Escribe el ssn en el campo correspondiente.
     * @param ssnn el ssn de registro a ingresar.
     */
    public void escribirSSN(String ssnn) {
        this.sendText(ssnn, ssn);
    }

    /**
     * Escribe el nombre de usuario en el campo correspondiente.
     * @param user el nombre de usuario a ingresar.
     */
    public void escribirUsuario(String user) {
        this.sendText(user, usuario);
    }

    /**
     * Escribe la contraseña en el campo correspondiente.
     * @param pass la contraseña de registro.
     */
    public void escribirContraseña(String pass) {
        this.sendText(pass, contraseña);
    }

    /**
     * Verifica la contraseña nuevamente en el campo correspondiente.
     * @param repass la contraseña de registro nuevamente.
     */
    public void escribirRecontraseña(String repass) {
        this.sendText(repass, recontraseña);
    }

    /**
     * Hace clic en el botón de registrarse.
     */
    public void clickRegistrarse()  {
        this.click(btnRegistrarse);
    }

    /**
     * Devuelve el mensaje mostrado cuando el registro es exitoso.
     * @return el texto del mensaje de registro exitoso.
     */
    public String registroExitoso() {
        String msjBienvenida = this.getText(mensajeBienvenida);
        String msjRegistro = this.getText(mensajeRegistro);
        System.out.println("Resultado del registro: "+ msjBienvenida+ " "+ msjRegistro);

        return msjRegistro;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa el nombre al registrarse.
     * @return el texto del mensaje de error al no ingresar el nombre.
     */
    public String msjNombreRequerido() {
        String msj = this.getText(mensajeNombreRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa el apellido al registrarse.
     * @return el texto del mensaje de error al no ingresar el apellido.
     */
    public String msjApellidoRequerido() {
        String msj = this.getText(mensajeApellidoRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa la direccion al registrarse.
     * @return el texto del mensaje de error al no ingresar la direccion.
     */
    public String msjDireccionRequerido() {
        String msj = this.getText(mensajeDireccionRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa la ciudad al registrarse.
     * @return el texto del mensaje de error al no ingresar la ciudad.
     */
    public String msjCiudadRequerido() {
        String msj = this.getText(mensajeCiudadRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa el estado al registrarse.
     * @return el texto del mensaje de error al no ingresar el estado.
     */
    public String msjEstadoRequerido() {
        String msj = this.getText(mensajeEstadoRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa el codigo postal al registrarse.
     * @return el texto del mensaje de error al no ingresar el codigo postal.
     */
    public String msjCodigoPostalRequerido() {
        String msj = this.getText(mensajeCodigoPostalRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa la ssn al registrarse.
     * @return el texto del mensaje de error al no ingresar la ssn.
     */
    public String msjSsnRequerido() {
        String msj = this.getText(mensajeSsnRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa el nombre de usuario al registrarse.
     * @return el texto del mensaje de error al no ingresar el nombre de usuario.
     */
    public String msjUsuarioRequerido() {
        String msj = this.getText(mensajeUsuarioRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa la contraseña al registrarse.
     * @return el texto del mensaje de error al no ingresar la contraseña.
     */
    public String msjContraseñaRequerido() {
        String msj = this.getText(mensajeContraseñaRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }

    /**
     * Devuelve el mensaje mostrado cuando no se ingresa la contraseña nuevamente al registrarse.
     * @return el texto del mensaje de error al no ingresar la contraseña nuevamente.
     */
    public String msjReContraseñaRequerido() {
        String msj = this.getText(mensajeRecontraseñaRequerido);
        System.out.println("Mensaje de error: "+ msj);
        return msj;
    }


}
