public class DocumentFactoryTest {

    interface Document {
        void open();
    }

    static class WordDocument implements Document {
        @Override
        public void open() { 
            System.out.println("Opening Word Document."); 
        }
    }

    static class PdfDocument implements Document {
        @Override
        public void open() { 
            System.out.println("Opening PDF Document."); 
        }
    }

    static class ExcelDocument implements Document {
        @Override
        public void open() { 
            System.out.println("Opening Excel Document."); 
        }
    }

    abstract static class DocumentFactory {
        public abstract Document createDocument();
    }

    static class WordFactory extends DocumentFactory {
        @Override
        public Document createDocument() { 
            return new WordDocument(); 
        }
    }

    static class PdfFactory extends DocumentFactory {
        @Override
        public Document createDocument() { 
            return new PdfDocument(); 
        }
    }

    static class ExcelFactory extends DocumentFactory {
        @Override
        public Document createDocument() { 
            return new ExcelDocument(); 
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 2: Factory Method Pattern ---");
        
        DocumentFactory wordFactory = new WordFactory();
        Document doc1 = wordFactory.createDocument();
        doc1.open();

        DocumentFactory pdfFactory = new PdfFactory();
        Document doc2 = pdfFactory.createDocument();
        doc2.open();

        DocumentFactory excelFactory = new ExcelFactory();
        Document doc3 = excelFactory.createDocument();
        doc3.open();

        System.out.println("--- Execution Finished Successfully! ---");
    }
}