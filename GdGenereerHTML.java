package gdgenereerhtml;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class GdGenereerHTML
{
    public static void main(String[] args)
    {
        try
        {
            File geselecteerdeDirectory = new File(args[0]);
//            File outputHTML = new File("/Users/gerard/Pictures/annDoetsFineArt/website/index.html");
            File outputHTML = new File(geselecteerdeDirectory, "index.html");
            FileWriter myWriter = new FileWriter(outputHTML);
            File[] listOfFiles = geselecteerdeDirectory.listFiles();
            Arrays.sort(listOfFiles, Comparator.comparing(File::getName, new FilenameComparator()));
            myWriter.write(preText);
            for (File fileInDir : listOfFiles)
            {
                if (isImage(fileInDir))
                {
//                    herschaalDeJPG(fileInDir);
//                    System.out.println(naamZonderPost(fileInDir.getName()) );
//                    myWriter.write("<td><a><img src='" + "src/" + fileInDir.getName() + "'</a></td><tr><td VALIGN=\"top\" HEIGHT=\"100\" >" + bewerkOutput(fileInDir.getName())
                    myWriter.write("<td><a><img src='" + "src/" + fileInDir.getName() + 
                        "'</a></td><tr><td class='onderschrift'>" + bewerkOutput(fileInDir.getName())
                        + "</td></tr><tr><td HEIGHT=\"60\"></td></tr>\n");
                }
            }
            myWriter.write(postText);
            myWriter.close();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(GdGenereerHTML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(GdGenereerHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void resizeFile(File  fileInDir,
//                              String imagePathToWrite, int resizeWidth, int resizeHeight)
    public static void resizeFile(File fileInDir)
        throws IOException
    {

    }

    
    public static BufferedImage scale(BufferedImage imageToScale) throws IOException {
         int dWidth = 400;
             int dHeight =600;
        File eruit = new File("src", "plaatje");
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        ImageIO.write(scaledImage, preText, eruit);
        return scaledImage;
    }
    
    
    
    static void herschaalDeJPG(File fileInDir) throws IOException
    {
        File eruit = new File("src", fileInDir.getName());
        Image imageRead = null;
//        BufferedImage imageRead =   null;
        imageRead = ImageIO.read(fileInDir);
//            RenderedImage = new RenderedImage. 

        imageRead = imageRead.getScaledInstance(400, -1, Image.SCALE_FAST);
      
        
//    ImageIO.write write( imageRead, "jpg", eruit);
//        return (eruit);
    }

    static String bewerkOutput(String erin)
    {
        String eruit;
        eruit = vervangMin(naamZonderPost(naamZonderPre(erin)));
        return (eruit);
    }

    static String vervangMin(String erin)
    {
        String eruit;
        eruit = erin;
        if (erin.contains("-"))
        {
            eruit = erin.replaceAll("-", " ");

        }
        return (eruit);

    }

    static String naamZonderPre(String erin)
    {
        String eruit;
        eruit = erin;
        if (erin.contains("_"))
        {
            String[] parts = erin.split("_");
            eruit = parts[1];
        }
        return (eruit);
    }

    static String naamZonderPost(String erin)
    {
        String eruit;
        eruit = erin;
//        System.out.println(erin);
        if (erin.contains("."))
        {
            String[] parts = erin.split(Pattern.quote(".")); // Split on period.
//            String[] parts = erin.split(".");
            eruit = parts[0];
        }
        return (eruit);
    }

    static boolean isImage(File erin)
    {
//        System.out.println("gdview.viewClass.isImage()" + erin.getAbsolutePath());
        Boolean eruit = Boolean.FALSE;
        String naamFile = erin.getName();
        if (naamFile.lastIndexOf(".") > 0)
        {
            try
            {
//                String extension = erin.getName().substring(erin.getName().lastIndexOf("."));
                String extension = naamFile.substring(naamFile.lastIndexOf("."));
                if (extension.toLowerCase().contains(".png"))
                {
                    eruit = Boolean.TRUE;
                } else
                {
                    if (extension.toLowerCase().contains(".jpg"))
                    {
                        eruit = Boolean.TRUE;
                    } else
                    {
                        if (extension.toLowerCase().contains(".jpeg"))
                        {
                            eruit = Boolean.TRUE;
                        } else
                        {
                            if (extension.toLowerCase().contains(".tiff"))
                            {
                                eruit = Boolean.TRUE;
                            }
                        }
                    }
                }
            } catch (java.lang.StringIndexOutOfBoundsException e)
            {
                try
                {
//                    Logger.getLogger(viewClass.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("gdview.viewClass.getFilesInDirectory() out of bounds exception " + erin.getCanonicalPath());
                } catch (IOException ex)
                {
//                    Logger.getLogger(viewClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return eruit;
    }
    
    
    static String preText = "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"\n" +
"<link href='https://fonts.googleapis.com/css?family=Averia Libre' rel='stylesheet'>"+
"<link href='https://fonts.googleapis.com/css?family=Alex Brush' rel='stylesheet'>\n" +
"<style>\n" +
"\n" +
"\n" +
"	body 	{ background-color: #696969;\n" +
"	     	}\n" +
"\n" +
"	h1   	{color: red;\n" +
"	      	font-family: ''Averia Libre';\n" +
"             	font-size: 60px;\n" +
"             	font-weight: 1;\n" +
"	      	}\n" +
"		\n" +
"\n" +
"\n" +
"	table 	{text-align: center;\n" +
"	      	 font-family: 'Alex Brush';\n" +
"              	font-size: 50px;\n" +
"              	font-weight: 1;\n" +
"              	color: red;\n" +
"		\n" +
"		}\n" +
".voorwoord {\n" +
"  text-align: left;\n" +
"  font-size: 50px;\n" +
"  color: white;\n" +
"}\n" +
"\n" +
".onderschrift{text-align: center;\n" +
"	      	 font-family: 'Alex Brush';\n" +
"              	font-size: 80px;\n" +
"              	font-weight: 1;\n" +
"              	color: #dbd46e;\n" +
"		}"+
"\n" +
"\n" +
"img {border: 20px solid ;\n" +
"    border-color: #ffe0cc ;\n" +
"	border-radius: 20px;\n" +
"	}\n" +
"</style>\n" +
"<title>Anne Doets Fine Art</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1>     Anne Doets Fine Art</h1>\n" +
"<table>\n" +
"    <tr><td class='voorwoord'>Dit zijn mijn schilderijen die ik maakte </td></tr>\n" +
"    <tr><td class='voorwoord'>tijdens mijn opleiding op de academie </td></tr>\n" +
"    <tr><td class='voorwoord'>voor realistische schilderkunst. </td></tr>\n" +
"    <tr><td class='voorwoord'>Deze opleiding leert je de ambachtelijke</td></tr>\n" +
"    <tr><td class='voorwoord'>schilderkunst zoals die al sinds eeuwen</td></tr>\n" +
"    <tr><td class='voorwoord'>beoefend wordt. De schildertechniek</td></tr>\n" +
"    <tr><td class='voorwoord'>dicteert dat het schilderij in lagen wordt </td></tr>\n" +
"    <tr><td class='voorwoord'>geschilderd. Tussentijds moet de laag </td></tr>\n" +
"    <tr><td class='voorwoord'>drogen. Daarna  weer de volgende laag. </td></tr>\n" +
"    <tr><td class='voorwoord'>Het vereist een planmatige aanpak en  </td></tr>\n" +
"    <tr><td class='voorwoord'>veel geduld. De droogtijd tussen de lagen</td></tr>\n" +
"    <tr><td class='voorwoord'> is 2 weken. <br>  </td></tr>";

    static String postText = "</tbody>\n"
        + "</table>\n"
        + "</body>\n"
        + "</html>";

}
