package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class BFS {
	int tempo;
	public DirectedSparseMultigraph<String, EdgeType> getWg() {
		return wg;
	}
	public void setWg(DirectedSparseMultigraph<String, EdgeType> wg) {
		this.wg = wg;
	}	
	private DirectedSparseMultigraph<String, EdgeType > wg;
/**
 * 
 * @param graphFile
 * carrega um grafo do arquivo de entrada
 */
public void load(String graphFile) {
	this.wg=new DirectedSparseMultigraph<String, EdgeType> ();
	
	BufferedReader buffread;
	 String linha;
	  List<String> vertices;
	  List<String> result;
	  int wei;
	  int i;
	  try {
		  /* obtem os vértices primeiro */
		  
			buffread = new BufferedReader(new FileReader(graphFile));
			i=0;
			boolean eof=false;
			/* obtem os vértices primeiro */
			linha=buffread.readLine();
			vertices = Arrays.asList(linha.split("\\s* \\s*"));
			for(String v: vertices) {
				this.wg.addVertex(v);
				
			}
			/* adiciona as arestas agora */
			i=0;
			while((linha=buffread.readLine())!=null) {
				result = Arrays.asList(linha.split("\\s* \\s*"));
				for(String v: result) {
					wei = Integer.valueOf(v);
					if(wei!=0) {
   					 EdgeType e = new EdgeType(wei);
					 this.wg.addEdge(e, vertices.get(i), vertices.get(result.indexOf(v)));
					} 

				}  				
			  
			   i++;
			   System.out.print("reg" + i + ": ");
			   System.out.println(result);
			   
			}
			buffread.close();
	                
       }

	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
} 
	
	/* for (String v: this.getGdep().getVertices()) {
		this.gdep.addVertex(v);
	}
	
	for ( Number e: c.getGdep().getEdges()) {
		Pair s= c.gdep.getEndpoints(e);
		String v1 = (String) s.getFirst();
		String v2 = (String) s.getSecond();
		this.gdep.addEdge(this.gdep.getEdgeCount(),v1, v2);
	}	
}
*/
	public void mostraGrafo1(DirectedSparseMultigraph g) {
		 // SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
	      // Layout<V, E>, VisualizationComponent<V,E>
		 Layout<Integer, String> layout = new CircleLayout(g);
	     layout.setSize(new Dimension(300,300));
	     //BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
	     BasicVisualizationServer vv = new BasicVisualizationServer<>(layout);
	     vv.setPreferredSize(new Dimension(350,350));       
	     // Setup up a new vertex to paint transformer...
	     Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
	         public Paint transform(String i) {
	             return Color.YELLOW;
	         }
	     };  
	     Transformer<EdgeType,String> edgePaint = new Transformer<EdgeType,String>() {
	         public String transform(EdgeType i) {
	        	    return  String.valueOf(i.weight);
	             
	         }
	     };
	     vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
	    // vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
	     vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	     
	     vv.getRenderContext().setEdgeLabelTransformer(edgePaint);
	     //vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	     vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);        
	     
	     JFrame frame = new JFrame("Visualização de um Grafo Ponderado");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.getContentPane().add(vv);
	     frame.pack();
	     frame.setVisible(true);  
	     
		
	}

/*
 * Esta visualização ignora os pesos do grafo	
 */
	public void mostraGrafo2(DirectedGraph<String, EdgeType> g) {
		 // SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
	      // Layout<V, E>, VisualizationComponent<V,E>
		 Layout<Integer, EdgeType> layout = new CircleLayout(g);
	     layout.setSize(new Dimension(300,300));
	    // BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer,String>(layout);
	     BasicVisualizationServer vv = new BasicVisualizationServer<>(layout);
	     vv.setPreferredSize(new Dimension(350,350));       
	     // Setup up a new vertex to paint transformer...
	     Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
	         public Paint transform(String i) {
	             return Color.YELLOW;
	         }
	     };  
	     Transformer<EdgeType,String> edgePaint = new Transformer<EdgeType,String>() {
	         public String transform(EdgeType i) {
	        	    return  "";
	             
	         }
	     };       	     
	     vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
	     vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	     vv.getRenderContext().setEdgeLabelTransformer(edgePaint);
	    // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	     // vv.getRenderContext().getEdgeIncludePredicate();
	     vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);        
	     
	     JFrame frame = new JFrame("Grafo de Dependência do Caminho");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.getContentPane().add(vv);
	     frame.pack();
	     frame.setVisible(true);   	    
		
	}

    class VData{
    	int cor, /* flag usado no algoritmo para cada vértice*/
    	td,      /* tempo de descoberta do vértice */
    	tt; /* tempo de termino do vértice */
    	String	pred; /* predecessor/antecessor do vértice na busca tanto no DFS quanto no BFS*/
        int dist;
    	
    }
    public void printVData(VData vd) {
    	System.out.println("cor pred td tt dist");
    	System.out.println(vd.cor + "    "+ vd.pred + "   "+ vd.td + "   "+ vd.tt+ "   "+ vd.dist);
    }
       
	 public void BFS(DirectedGraph<String, EdgeType> g) {
		 HashMap<String,VData> dVertics = new HashMap<String,VData>();
		 /* inicializando os dados dos vértices */
		   for(String u: g.getVertices()) {
			   VData vd = new VData();
			   vd.cor=0; // 0 é branco
			   vd.dist=0;
			   vd.pred=null;
			   dVertics.put(u, vd);
		   }
		   for(String u: g.getVertices()) {
			   if(dVertics.get(u).cor==0) {// verifico se a cor é branca
				   visitaBFS(g,dVertics,u);
			   }
		   }
		   
		   /*
		    * Adiciono os vértices
		    */
		   DirectedGraph<String, EdgeType> gBFS=new DirectedSparseMultigraph<String, EdgeType> ();
		   for(String u: g.getVertices()) {
			   printVData(dVertics.get(u));
			   gBFS.addVertex(u);
			   
		   }
	/*
	 * adiciono as arestas 	    
	 */
		   for(String u: g.getVertices()) {
			   String v =dVertics.get(u).pred;
			   if(v!=null) {
				   EdgeType e = new EdgeType(1);
				   gBFS.addEdge(e,v, u);
				   
			   }
		   }  
		   
		  /*
		   * chama o mostra grafo 2 para exibir o grafo gerado pelo conjunto de antecessores gerado pel DFS 
		   */
		   this.mostraGrafo2(gBFS);
	   }
		      
	   public void visitaBFS(DirectedGraph<String, EdgeType> g,HashMap<String, VData> dVertics, String u) {
		   VData ud = dVertics.get(u);
		   Queue<String> fila = new LinkedList<String>();
			ud.cor = 1; //cinza
			ud.dist = 0;
			fila.clear();
			fila.add(u);
			while(fila.size() > 0) { 
				u = fila.remove();
				for(String v: g.getNeighbors(u)) { // pega lista de adjacentes de u
					VData vd = dVertics.get(v);
					if (vd.cor==0) {// se a cor do adjacente é branco
						vd.cor = 1;
						vd.dist = ud.dist+1;
						vd.pred = u;
						fila.add(v);
					}
				}
				ud.cor = 2;
			}
	   }
	   
	public static void main(String[] args) {
		BFS g = new BFS();
		g.load("g1.txt");
		g.mostraGrafo1(g.wg);
		g.BFS(g.wg);
	}
 
}
