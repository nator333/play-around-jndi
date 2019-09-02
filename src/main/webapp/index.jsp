<html>
<body>
<h2>Hello World!</h2>
<p>by Tomcat</p>
<ul>
    <li>Play around with JNDI</li>
    <li>Learn how to leverage JNDI</li>
</ul>

<p><% out.print("JSP"); %></p>
<p>8 x 8 = <%= 8 * 8 %></p>
<p>Time: <%= new java.util.Date() %></p>
<p>Host requested: <%= request.getHeader("Host") %></p><p><% out.print("Hello, world!"); %></p>

</body>
</html>
