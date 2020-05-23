
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="uploadMB")
@RequestScoped
public class FileUploadView implements Serializable{
    private UploadedFile file;
    private String conteudo;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload() throws IOException{
        if (file != null) {
            conteudo= new Scanner(file.getInputstream(),"UTF-8").useDelimiter("\\A").next();
            FacesMessage message = new FacesMessage("Sucesso", file.getFileName() + " foi upado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String teste(){
        return conteudo;
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException{
        file=event.getFile();
        conteudo = new Scanner(file.getInputstream(),"UTF-8").useDelimiter("\\A").next();
        FacesMessage msg = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi upado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
