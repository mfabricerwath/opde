package controleur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
@ViewScoped
public class ImageClass {

	public static void uploadImage(FileUploadEvent event) throws ServletException, IOException {
		// Do what you want with the file
		try {
			copyImage(event.getFile().getFileName(), event.getFile().getInputstream());
			// this.img.setImage(event.getFile().getFileName());
			System.out.println("success" + event.getFile().getFileName()+ " is uploaded.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void copyImage(String fileName, InputStream in) {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String destinationper = servletContext.getRealPath("/images")+ File.separator+ "agent"+ File.separator;
	try {  System.out.println(destinationper);
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destinationper+ fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
