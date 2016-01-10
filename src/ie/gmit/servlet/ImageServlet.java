package ie.gmit.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.CloudRenderer;
import ie.gmit.sw.DrawingPattern;
import ie.gmit.sw.GaussianRotatingPlacer;
import ie.gmit.sw.LogarithmicSpiralPlacerImpl;
import ie.gmit.sw.SourceType;
import ie.gmit.sw.UrlParserImpl;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CloudRenderer renderer = new CloudRenderer("http://www.gmit.ie/", SourceType.WEB, DrawingPattern.LOGARITHMIC_SPIRAL, "newImage");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("image/png");
		
		renderer.setParser(new UrlParserImpl());
		
		if(request.getParameter("url") != null){
			String source = request.getParameter("url");
			
			if(!source.startsWith("http")){
				source = "http://"+source;
			}
			
			renderer.parse(source);
		}
		
		if(request.getParameter("pattern").equals("log")){
			renderer.setPlacer(new LogarithmicSpiralPlacerImpl());
		}else{
			renderer.setPlacer(new GaussianRotatingPlacer());
		}
		
		renderer.draw();

	
		File f = new File("/home/john/newImage.png");
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "png", out);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
