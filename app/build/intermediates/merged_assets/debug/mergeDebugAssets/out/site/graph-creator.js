var shift_pressionado = false;
var  modo_adicionar_link  = false;
var  modo_adicionar_no = false;
var  modo_drag = false;
var  modo_configurar_link = false;
var  modo_editar_no = false;
var  modo_deletar_no = false;
var  modo_deletar_aresta = false;

var efeito_aresta_curva = false;

var raio_circulo = 30;
var tamanho_icone = 40;

var mouseDownNode =  null;
var thisGraph = null;
var selectedNode = null;
var mouseDownLink = null;

function upgrade_js() {
     //var a = document.getElementById("graph").style.backgroundColor = "red";
     get_qual_tipo_novo_no_js();
 }

function configurar_status_teclado(a, b, c, d, e, f, g) {
     modo_adicionar_no = a;
     modo_adicionar_link  = b;
     modo_drag = c;
     modo_configurar_link = d;
     modo_editar_no = e;
     modo_deletar_no = f;
     modo_deletar_aresta = g;
}


function form_graph_js() {
       var saveEdges = [];
       thisGraph.edges.forEach(function(val, i){
          saveEdges.push({source: val.source.id, target: val.target.id});
       });
       var a = window.JSON.stringify({"nodes": thisGraph.nodes, "edges": saveEdges});
       testando = a;
       return ("" + a);
}

var testando = null;


 function load_graph_js( txtRes){
       var jsonObj = JSON.parse(txtRes);

       thisGraph.deleteGraph(true);
       thisGraph.nodes = jsonObj.nodes;
       thisGraph.setIdCt(jsonObj.nodes.length + 1);
       var newEdges = jsonObj.edges;

       newEdges.forEach(function(e, i){
            newEdges[i] = {source: thisGraph.nodes.filter(function(n){return n.id == e.source;})[0],
            target: thisGraph.nodes.filter(function(n){return n.id == e.target;})[0]};
       });
//                         AndroidInterface.print("asqwer " + txtRes);
       thisGraph.edges = newEdges;
       thisGraph.updateGraph();
  };


    // handle delete graph
function delete_graph_js() {
     thisGraph.deleteGraph(true);
 };



function get_qual_tipo_novo_no_js() {
	var tipo_no = AndroidInterface.get_qual_tipo_novo_node_java();
//       AndroidInterface.print(" asd:" + thisGraph);
//    var tipo_no = 2;
//    var tipo_no = parseInt(prompt("Qual valor?"));
    if (tipo_no == 1 || tipo_no == 2 || tipo_no == 3 || tipo_no == 4  || tipo_no == 5 ){
	    configurar_status_teclado(true, false, false, false, false, false, false);
	} else if (tipo_no == 101) {
	    configurar_status_teclado(false, true, false, false, false, false, false);
	    efeito_aresta_curva= true;
	}  else if (tipo_no == 102) {
	    efeito_aresta_curva = false;
	    configurar_status_teclado(false, true, false, false, false, false, false);
	}  else if (tipo_no == 103) {
	    configurar_status_teclado(false, false, false, true, true, false, false);
	} else if (tipo_no == 200) {
	    configurar_status_teclado(false, false, true, false, false, false, false);
	} else if (tipo_no == 0) {
        configurar_status_teclado(false, false, false, false, false, false, false);
    } else if (tipo_no == 300) {
        configurar_status_teclado(false, false, false, false, false, true, true);
    }
	 return tipo_no;
}





var principal = function(d3, saveAs, Blob, undefined){
  var consts = {
    defaultTitle: "  "
  };

  function linkArc(sx, sy, tx, ty, d) {
	var dx = tx  - sx;
	var dy = ty - sy;
	var dr = Math.sqrt(dx * dx + dy * dy);
	if (d.modelo === "101") {
    	var resposta = 'M' + sx + ',' + sy + "A" + dr + "," + dr + " 0 0,1 " + tx + ',' + ty;
        return  resposta;
	} else if (d.modelo === "102"){
	    var resposta =  'M' + sx + ',' + sy + 'L' + tx + ',' + ty;
        return  resposta;
	}
  }
  
  //eu fiz
  function pegador_foto(d) {
	if (d.type == 1) {
		return "imgs/ic_circulo_seta_baixo.png";
	}else if (d.type == 2) {
		return "imgs/ic_circulo_seta_cima.png";
    }else if (d.type == 3) {
		return "imgs/ic_circulo_seta_direita.png";
	}else if (d.type == 4) {
		return "imgs/ic_circulo_seta_esquerda.png";
	}else if (d.type == 5) {
		return "imgs/ic_circulo_seta_stop.png";
	}else if (d.type == 6) {
     	return "imgs/ic_start.png";
    }else if (d.type == 7) {
     	return "imgs/ic_circle_chegada.png";
    }
  }

  // define graphcreator object
  var GraphCreator = function(svg, nodes, edges){
    thisGraph = this;
    thisGraph.idct = 0;

    thisGraph.nodes = nodes || [];
    thisGraph.edges = edges || [];

    // define arrow markers for graph links... cabeca da aresta dps q ela eh formada
    var defs = svg.append('svg:defs');
    defs.append('svg:marker')
      .attr('id', 'end-arrow')
      .attr('viewBox', '0 -5 10 10')
      .attr('refX', "32")
      .attr('markerWidth', 3.5)
      .attr('markerHeight', 3.5)
      .attr('orient', 'auto')
      .append('svg:path')
      .attr('d', 'M0,-5   L10,0   L0,5');


    thisGraph.svg = svg;
    thisGraph.svgG = svg.append("g").classed(thisGraph.consts.graphClass, true);
    var svgG = thisGraph.svgG;

		
    // displayed when dragging between nodes... desenho da linha
    thisGraph.dragLine = svgG.append('svg:path')
          .attr('class', 'link')
          .style('marker-end', 'url(#mark-end-arrow)');

    // svg nodes and edges	
    thisGraph.paths   = svgG.append("g").selectAll("g");
    thisGraph.circles = svgG.append("g").selectAll("g");

    thisGraph.drag = d3.behavior.drag()
          .origin(function(d){
            return {x: d.x, y: d.y};
          }).on("drag", function(args){
                thisGraph.dragmove.call(thisGraph, args);
          });

    svg.on("touchstart"  , function(d){thisGraph.svgMouseUp.call(thisGraph, d)});
  };
  
  GraphCreator.prototype.setIdCt = function(idct){
    this.idct = idct;
  };

  GraphCreator.prototype.consts =  {
    selectedClass: "selected",
    circleGClass: "conceptG",
    graphClass: "graph",
    activeEditId: "active-editing",
    nodeRadius: raio_circulo
  };

GraphCreator.prototype.deleteGraph = function(skipPrompt){
    var thisGraph = this,
        doDelete = true;
    if (!skipPrompt){
      doDelete = window.confirm("Press OK to delete this graph");
    }
    if(doDelete){
      thisGraph.nodes = [];
      thisGraph.edges = [];
      thisGraph.updateGraph();
    }
  };


  /* PROTOTYPE FUNCTIONS */
  GraphCreator.prototype.dragmove = function(d) {
	var thisGraph = this;
    if (modo_adicionar_link){
		// var curvatura_link = linkArc(d.x, d.y, parseInt(d3.mouse(thisGraph.svgG.node())[0]), parseInt(d3.mouse(this.svgG.node())[1]));
        // thisGraph.dragLine.attr('d', curvatura_link);
    } else if (modo_drag) {
      d.x += d3.event.dx;
      d.y +=  d3.event.dy;
      thisGraph.updateGraph();
    }
  };


  GraphCreator.prototype.pathMouseDown = function(d3path, d){
    var thisGraph = this;
		d3.event.stopPropagation();

    if (d &&  (modo_deletar_no || modo_deletar_aresta)){
       thisGraph.edges.splice(thisGraph.edges.indexOf(d), 1);
       thisGraph.updateGraph();
    }
  };



  GraphCreator.prototype.circleMouseDown = function(d3node, d){
    var thisGraph = this;
    d3.event.stopPropagation();

    if (modo_adicionar_link && mouseDownNode){
       if (mouseDownNode !== d){
           var newEdge;
           if (efeito_aresta_curva) {
               newEdge = {source: mouseDownNode, target: d, modelo:"101"};
           } else {
               newEdge = {source: mouseDownNode, target: d, modelo:"102"};
           }
           var filtRes = thisGraph.paths.filter(function(d){
               if (d.source === newEdge.target && d.target === newEdge.source){
                    thisGraph.edges.splice(thisGraph.edges.indexOf(d), 1);
               }
                return d.source === newEdge.source && d.target === newEdge.target;
           });
           if (!filtRes[0].length){
               thisGraph.edges.push(newEdge);
               thisGraph.updateGraph();
           }
       }
       thisGraph.updateGraph();
       mouseDownNode  = null;
       return;
    } else if (modo_adicionar_link ){
       	  //var curvatura_link = linkArc(d.x, d.y, d.x, d.y)
          //thisGraph.dragLine.classed('hidden', false).attr('d', curvatura_link);
          mouseDownNode = d;
          return;
     } else if (modo_editar_no || modo_configurar_link) {
          var s = AndroidInterface.edit_node_arrow_java(d.id, d.title, d.type, d.set, d.value);
     } else if (modo_deletar_no || modo_deletar_aresta) {
        if (d){
            thisGraph.spliceLinksForNode(d);
            thisGraph.nodes.splice(thisGraph.nodes.indexOf(d), 1);
            //state.selectedNode = null;
            thisGraph.updateGraph();
        }
     }
     mouseDownNode  = null;
  };

  // mouseup on main svg
  GraphCreator.prototype.svgMouseUp = function(){
    var thisGraph = this;
    if (modo_adicionar_no){
      // clicked not dragged from svg
      var tipo_no = get_qual_tipo_novo_no_js();
      var xycoords = d3.mouse(thisGraph.svgG.node());
		  var d = {id: thisGraph.idct++, title: consts.defaultTitle, x: xycoords[0], y: xycoords[1], type: tipo_no, set: "1", value: "0"};
    	  thisGraph.nodes.push(d);
		  thisGraph.updateGraph();
    } else if (modo_adicionar_no || modo_adicionar_link){
          // dragged from node
          thisGraph.dragLine.classed("hidden", true);
    }
  };


  // remove edges associated with a node
  GraphCreator.prototype.spliceLinksForNode = function(node) {
    var thisGraph = this;
    var toSplice = thisGraph.edges.filter(function(l) {
      return (l.source === node || l.target === node);
    });
    toSplice.map(function(l) {
          thisGraph.edges.splice(thisGraph.edges.indexOf(l), 1);
    });
  };

  // call to propagate changes to graph
  GraphCreator.prototype.updateGraph = function(){
    var thisGraph = this,
        consts = thisGraph.consts;

    thisGraph.paths = thisGraph.paths.data(thisGraph.edges, function(d){
      return String(d.source.id) + "+" + String(d.target.id);
    });
    var paths = thisGraph.paths;
    // update existing paths
    paths.style('marker-end', 'url(#end-arrow)')
      .attr("d", function(d){
		var curvatura_link = linkArc(parseInt(d.source.x), parseInt(d.source.y), parseInt(d.target.x), parseInt(d.target.y), d);
		return curvatura_link;
      });

    // add new paths
    paths.enter()
      .append("path")
      .style('marker-end','url(#end-arrow)')
      .classed("link", true)
      .attr("d", function(d){
		var curvatura_link = linkArc(parseInt(d.source.x), parseInt(d.source.y), parseInt(d.target.x), parseInt(d.target.y), d);
		return curvatura_link;
      })
      .on("touchstart", function(d){
           thisGraph.pathMouseDown.call(thisGraph, d3.select(this), d);
        }
      );

    paths.exit().remove();

    // update existing nodes
    thisGraph.circles = thisGraph.circles.data(thisGraph.nodes, function(d){ return d.id;});
    thisGraph.circles.attr("transform", function(d){return "translate(" + d.x + "," + d.y + ")";});

    // add new nodes //definicao da estrutyra de um novo n
    var newGs= thisGraph.circles.enter().append("g");

    newGs.classed(consts.circleGClass, true)
      .attr("transform", function(d){return "translate(" + d.x + "," + d.y + ")";})
      .on("touchstart", function(d){
        thisGraph.circleMouseDown.call(thisGraph, d3.select(this), d);
      })
      .call(thisGraph.drag);

    newGs.append("circle")
      .attr("r", String(consts.nodeRadius));

	newGs.append("svg:image")
		.attr("xlink:href", pegador_foto)
        .attr("x", function(d) { return (-1*tamanho_icone/2);})
        .attr("y", function(d) { return (-1*tamanho_icone/2);})
        .attr("height", tamanho_icone)
        .attr("width", tamanho_icone);	
		
		
    newGs.each(function(d){
      thisGraph.insertTitleLinebreaks(d3.select(this), d.title);
    });

    thisGraph.circles.exit().remove();
  };

  
  GraphCreator.prototype.zoomed = function(){
    d3.select("." + this.consts.graphClass)
      .attr("transform", "translate(" + d3.event.translate + ") scale(" + d3.event.scale + ")");
  };

  GraphCreator.prototype.updateWindow = function(svg){
    var docEl = document.documentElement,
        bodyEl = document.getElementsByTagName('body')[0];
    var x = window.innerWidth || docEl.clientWidth || bodyEl.clientWidth;
    var y = window.innerHeight|| docEl.clientHeight|| bodyEl.clientHeight;
    svg.attr("width", x).attr("height", y);
  };


  /**** MAIN ****/
  // warn the user when leaving
  window.onbeforeunload = function(){
    return "Make sure to save your graph locally before leaving :-)";
  };

  var docEl = document.documentElement,
     bodyEl = document.getElementsByTagName('body')[0];
  var width = window.innerWidth || docEl.clientWidth || bodyEl.clientWidth,
      height =  window.innerHeight|| docEl.clientHeight|| bodyEl.clientHeight;

  // initial node data
  var nodes = [];
  var edges = [];
  /** MAIN SVG **/
  var svg = d3.select("#graph").append("svg").attr("width", width).attr("height", height);
  //var svg = d3.select("#graph").append("svg").attr("width", "720").attr("height", "720");
  var graph = new GraphCreator(svg, nodes, edges);
      graph.setIdCt(2);
  graph.updateGraph();
}

document.onload = principal(window.d3, window.saveAs, window.Blob);
