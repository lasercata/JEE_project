# JEE project

Group name: `Tonnerre de Zeus`
lastest branch: `main`

## Setup
### Database connection
#### Config file for the java part
Add a file `config.properties` in the folder `code/src/main/java/` with the following content :
```
JDBC_URL=jdbc:mysql://[hostname]:[port]/[folder]
DB_LOGIN=[username]
DB_PASSWORD=[password]
DB_DRIVER=com.mysql.cj.jdbc.Driver
```

Replace all the things in brackets with their actual value.

#### Population scripts
To connect to the database in command line, you can run the script `connect_to_db.sh`:
```
./code/connect_to_db.sh
```

To run a script in the DB, you can use `run_sql_script.sh`:
```
./code/run_sql_script.sh code/sql_scripts/[scriptname].sql
```
where you replace `[scriptname]` with the name of your script.

It is needed to be in the root folder of the project to run those scripts and to have created correctly the `config.properties` file, because the bash scripts read it.


To create and populate the database, you can use the script `run_all.sql` :
```
./code/run_sql_script.sh code/sql_scripts/run_all.sql
```

#### Trigger tests
To test the triggers, we have implemented some sql scripts, in the folder `code/sql_scripts/tests`.

To test the corresponding feature, run the script with `code/run_sql_script.sh`. It should give the error explained in comment in the corresponding file.

### Setting up a Tomcat Apache server for eclipse

1. Launch Eclipse. If you end up on the guide page, click on the orange triangle in the top right corner.

2. In the Project Explorer tab, select *"Create a Dynamic Web Project"* (if you cannot see the Project Explorer Tab, you can open it in *Window / Show View / Project Explorer*).

3. Choose a name for your project, and set the *Dynamic web module version* to 4.0. Click "Next".

4. In the *Java* tab, click "Next". In the *Web Module* tab, click "Finish".

5. You can now see your project in the Project Explorer. In *yourproject/src/main/webapp*, create a new HTML file (right click on *webapp* -> New -> HTML File) named "index.html". Click "Finish".

6. Edit your index.html file as you like; for now, you can simply add a `<h1>` title in the body of your file. The point is to simply be able to recognize your page once it appears, to make sure it works properly.

7. Make sure the *Server* tab is open in the bottom of your screen. If you cannot see it, go to *Window / Show View / Server*, or in *Window / Show View / Other*, then find the *Server* folder, which contains the required tab.

8. In the Server tab, click on "No server available [...]". A new tab should open. In the "Select the server type" window, choose *Apache / Tomcat v10.1 Server*. Click "Next".

9. Choose an installation directory for your server. For example, you can create a *tomcat* folder in the home directory of your project (not in your workspace) and use it as a installation directory. Once you've chosen your folder, click "Next".

10. In the "Add and Remove" tab, your project should appear in the left tab. Select it, and click "Add". The project will be linked to your Apache server. Click "Finish".

11. Your server should nw appear in the "Server" tab. By expanding it, you should be able to see your project linked to it. Click on your server (not your project !), and click on "Start the server" in the top right corner of the "Server" tab (for keyboard lovers, Ctrl + Alt + R should do the trick).

12. Do not be scared by the red text appearing in your console. Those are not errors, simply poor design choices. Wait a second for the server to start. It should use your port 8080; you can check the port in the "Console" tab in the bottom of your screen.

13. In your web browser, open "localhost:8080/yourproject". You should now be able to see the edits you made during step 6. Your apache server is now set up !
