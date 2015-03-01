<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
 <jsp:include page="header.jsp"/>
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			 <div id="main" role="main">
			 
				 <jsp:include page="messages.jsp"/>
			 
			      <ul id="tiles">
			     	<c:forEach items="${listBook}" var="book">
			        <!-- These are our grid blocks -->
			        <li onclick="location.href='book/detail/${book.idBook}';">
			        <img src="${pageContext.request.contextPath}/imageDisplay/${book.idBook}">
			        	<div class="post-info">
			        		<div class="post-basic-info">
				        		<h3><a href="#">${book.title}</a></h3>
				        		<span><a href="#"><label> </label>${book.type}</a></span>
				        		<p>${fn:substring(book.description, 0, 150)}... <a href="book/detail/${book.idBook}">Plus</a></p>
			        		</div>
			        		<c:if test="${pageContext['request'].userPrincipal != null}">
				        		<div class="post-info-rate-share">
				        			<div class="text-center"><a href="${pageContext.request.contextPath}/evaluation/new/${book.idBook}"><span class="glyphicon glyphicon-star"></span> Noter ce livre <span class="glyphicon glyphicon-star"></span></a></div>
				        			<div class="clear"> </div>
				        		</div>
			        		</c:if>
			        	</div>
			        </li>
			        </c:forEach>
			        <!-- End of grid blocks -->
			      </ul>
			    </div>
			</div>
		</div>
		<!---//End-content---->
		<!----wookmark-scripts---->
		  <script src="${pageContext.request.contextPath}/resources/js/jquery.imagesloaded.js"></script>
		  <script src="${pageContext.request.contextPath}/resources/js/jquery.wookmark.js"></script>
		  <script type="text/javascript">
		    (function ($){
		      var $tiles = $('#tiles'),
		          $handler = $('li', $tiles),
		          $main = $('#main'),
		          $window = $(window),
		          $document = $(document),
		          options = {
		            autoResize: true, // This will auto-update the layout when the browser window is resized.
		            container: $main, // Optional, used for some extra CSS styling
		            offset: 20, // Optional, the distance between grid items
		            itemWidth:280 // Optional, the width of a grid item
		          };
		      /**
		       * Reinitializes the wookmark handler after all images have loaded
		       */
		      function applyLayout() {
		        $tiles.imagesLoaded(function() {
		          // Destroy the old handler
		          if ($handler.wookmarkInstance) {
		            $handler.wookmarkInstance.clear();
		          }
		
		          // Create a new layout handler.
		          $handler = $('li', $tiles);
		          $handler.wookmark(options);
		        });
		      }
		      /**
		       * When scrolled all the way to the bottom, add more tiles
		       */
		      function onScroll() {
		        // Check if we're within 100 pixels of the bottom edge of the broser window.
		        var winHeight = window.innerHeight ? window.innerHeight : $window.height(), // iphone fix
		            closeToBottom = ($window.scrollTop() + winHeight > $document.height() - 100);
		
		        if (closeToBottom) {
		          // Get the first then items from the grid, clone them, and add them to the bottom of the grid
		          var $items = $('li', $tiles),
		              $firstTen = $items.slice(0, 10);
		
		          applyLayout();
		        }
		      };
		
		      // Call the layout function for the first time
		      applyLayout();
		
		      // Capture scroll event.
		      $window.bind('scroll.wookmark', onScroll);
		    })(jQuery);
		  </script>
		<!----//wookmark-scripts---->
 		<jsp:include page="footer.jsp"/>
		<!---//End-wrap---->
	</body>
</html>