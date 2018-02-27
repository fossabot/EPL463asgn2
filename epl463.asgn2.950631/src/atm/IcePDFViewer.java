/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 * Class to create a JPanel for viewing PDF file
 * 
 * @author EPL463
 */
public class IcePDFViewer {
	/**
	 * IcePDFViewer singleton instance
	 */
	private static IcePDFViewer instance = null;

	/**
	 * private and empty constructor
	 */
	private IcePDFViewer() {
	}

	/**
	 * Get unique singleton instance method
	 * 
	 * @return instance of IcePDFViewer
	 */
	public static IcePDFViewer getIcePDFViewer() {
		if (instance == null) {
			instance = new IcePDFViewer();
		}
		return instance;
	}

	/**
	 * Creates the JPanel to view PDF file
	 * 
	 * @param filePath
	 *            pdf file path
	 * @return JPanel
	 */
	public JPanel createViewer(String filePath) {

		// build a controller
		SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);
		JPanel viewerComponentPanel= new JPanel();
		viewerComponentPanel = factory.buildViewerPanel();
		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));



		// Open a PDF document to view
		controller.openDocument(filePath);

		return viewerComponentPanel;
	}

}
