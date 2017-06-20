$(document).ready(function() {
    console.debug("Web is ready");

    var URL_PREFIX = "http://localhost:4567/booksearcher/books";
    var FIND_BY_FILTER = "/find/filter";
    var FIND_BY_ISBN = "/find/isbn";
    var FIND_ALL = "/find/all";

    /*
     * builds a book panel with the result json objects
     */
    function buildBook( book ){

      var title = $("<b></b>").text( book.title );
      var isbn = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "ISBN:" ) );
      var description = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "Description:" ) );
      var authors = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "Author(s):" ) );
      var editorial = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "Editorial:" ) );
      var categories = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "Categories:" ) );
      var edition = $("<div></div>").attr('class','col-sm-2').append( $("<b></b>").text( "Edition:" ) );

      var isbnVal = $("<div></div>").attr('class','col-sm-10').text( book.isbn );
      var descriptionVal = $("<div></div>").attr('class','col-sm-10').text( book.description );
      var authorsVal = $("<div></div>").attr('class','col-sm-10');
      for (var i = 0; i < book.authors.length; i++) {
        authorsVal.append( $("<div></div>").text( book.authors[i].name ) );
      }
      var editorialVal = $("<div></div>").attr('class','col-sm-10').text( book.editorial );
      var categoriesVal = $("<div></div>").attr('class','col-sm-10').text( book.classifications );
      var editionVal = $("<div></div>").attr('class','col-sm-10').text( book.edition );

      var isbnRow = $("<div></div>").attr('class','row').append( isbn, isbnVal );
      var descriptionRow = $("<div></div>").attr('class','row').append( description, descriptionVal );
      var authorsRow = $("<div></div>").attr('class','row').append( authors, authorsVal );
      var editorialRow = $("<div></div>").attr('class','row').append( editorial, editorialVal );
      var categoriesRow = $("<div></div>").attr('class','row').append( categories, categoriesVal );
      var editionRow = $("<div></div>").attr('class','row').append( edition, editionVal );

      var content = $("<div></div>").append( isbnRow, descriptionRow, authorsRow, editorialRow, categoriesRow, editionRow );
      var panel = $("<div></div>").attr('class', 'panel panel-primary');
      var panelHead = $("<div></div>").attr('class', 'panel-heading');
      var panelBody = $("<div></div>").attr('class', 'panel-body').append( content );

      panelHead.append( title );
      panel.append(panelHead, panelBody);

      return panel;
    }

    /*
     * finds by filters
     */
    function searchByFilter( filter ){

      var getting = $.get( URL_PREFIX + FIND_BY_FILTER + "/" + filter , null, null, "json" );

      getting.done( function( data ){
        if ( data ) {
          $("#books").append( $("<h3></h3>").text("Search result for: " + filter.split(":")[1])).append( $("<hr />") );
          for (var i = 0; i < data.length; i++) {
            $("#books").append( buildBook( data[i] ) );
          }
        }
      });

    }

    /*
     * finds by ISBN
     */
    function searchByISBN( isbn ){

      var getting = $.get( URL_PREFIX + FIND_BY_ISBN + "/" + isbn , null, null, "json" );

      getting.done( function( data ){
        if ( data ) {
          $("#books").append( $("<h3></h3>").text("Search result for: " + isbn )).append( $("<hr />") );
          $("#books").append( buildBook( data ) );
        }
      });

    }

    /*
     * finds all
     */
    function search(){

      var getting = $.get( URL_PREFIX + FIND_ALL , null, null, "json" );

      getting.done( function( data ){
        if ( data ) {
          $("#books").append( $("<h3></h3>").text("Search result")).append( $("<hr />") );
          for (var i = 0; i < data.length; i++) {
            $("#books").append( buildBook( data[i] ) );
          }
        }
      });
    }

    $("#searcher").click( function(){

      $("#books").empty();

      //get all values
      var title  = $("#title").val();
      var isbn  = $("#isbn").val();
      var description  = $("#description").val();
      var author  = $("#author").val();
      var editorial  = $("#editorial").val();
      var category  = $("#classification").val();

      //the filter can be just one per search
      if( title ){
        var data = "title:" + title;
        searchByFilter( data );
      }else if ( isbn ) {
        searchByISBN( isbn );
      }else if ( description ) {
        var data = "description:" + description;
        searchByFilter( data );
      }else if ( author ) {
        var data = "author:" + author;
        searchByFilter( data );
      }else if ( editorial ) {
        var data = "editorial:" + editorial;
        searchByFilter( data );
      }else if ( category ) {
        var data = "classification:" + category;
        searchByFilter( data );
      }else{
        search();
      }

    });
});
