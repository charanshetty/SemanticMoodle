package model;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.reasoner.rulesys.builtins.Equal;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;



public class extractowl extends Object{

	 public static  InfModel getData ()throws SQLException,NullPointerException, IOException
	{


		 OntDocumentManager mgr = new OntDocumentManager();
		 System.out.println("I m here in get data2");
		 OntModelSpec s = new OntModelSpec(OntModelSpec.OWL_MEM);
		
		 s.setDocumentManager(mgr);
		 OntModel m = ModelFactory.createOntologyModel(s, null);
			
		InputStream in = FileManager.get().open("/home/charan/Downloads/software/owlDM/V3/moodle_V3_inf.owl");

        
		 if (in == null) {
		 throw new IllegalArgumentException("File: " + in + " notfound");
		 }
		 m.read(in, "");
		 in.close();
		 System.out.println("I m here in get data`4");

 return m;
	}
	 
	 public static  OntModel getmodel ()throws SQLException,NullPointerException, IOException
		{


			 OntDocumentManager mgr = new OntDocumentManager();
			 System.out.println("I m here in get data2");
			 OntModelSpec s = new OntModelSpec(OntModelSpec.OWL_MEM);
			
			 s.setDocumentManager(mgr);
			 OntModel m = ModelFactory.createOntologyModel(s, null);
				
			InputStream in = FileManager.get().open("/home/charan/Downloads/software/owlDM/V3/moodle_V3_inf.owl");

	        
			 if (in == null) {
			 throw new IllegalArgumentException("File: " + in + " notfound");
			 }
			 m.read(in, "");
			 in.close();
			 System.out.println("I m here in get data`4");

	 return m;
		}

	 
	 
		public static ArrayList<OntClass> getClasses(OntModel m) {
			ArrayList<OntClass> classes = new ArrayList<OntClass>();
			Iterator<OntClass> net = m.listNamedClasses();
			while (net.hasNext()) {
				OntClass ontclass = net.next();
				System.out.println(ontclass.getLocalName());
				classes.add(ontclass);
				
			}

			return classes;
		}


	       
	        
	        
	    }
	
	
	
	
	