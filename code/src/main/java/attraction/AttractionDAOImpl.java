package attraction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DBManager;
import utils.GeneralDAO;

public class AttractionDAOImpl implements GeneralDAO<AttractionClass> {
    private AttractionClass readQueryResult(ResultSet rs) throws SQLException {
        int id1 = rs.getInt("id");
        String name = rs.getString("name");
        typeAttraction type = typeAttraction.valueOf(rs.getString("type"));
        double sizeAlone = rs.getDouble("sizealone");
        double sizeWithAdult = rs.getDouble("sizewithadult");
        String[] openingHoursFields = {"moOP","tuOP","weOP","thOP","frOP","saOP","suOP"};
        String[] closingHoursFields = {"moCL","tuCL","weCL","thCL","frCL","saCL","suCL"};
        ArrayList<String> openingHours = new ArrayList<String>();
        ArrayList<String> closingHours = new ArrayList<String>();

        for (String hour : openingHoursFields) {
            openingHours.add(rs.getString(hour));
        }

        for (String hour : closingHoursFields) {
            closingHours.add(rs.getString(hour));
        }

        //TODO: convert to Schedule here, and change the constructor
        AttractionClass result = new AttractionClass(id1,name,type,sizeAlone,sizeWithAdult,openingHours,closingHours);
        return result;
    }

    @Override
    public ArrayList<AttractionClass> getAll() {
        Connection connexion = DBManager.getInstance().getConnection();

        ArrayList<AttractionClass> allList = new ArrayList<>();

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select " + AttractionClass.sqlFields() + " from " + AttractionClass.getTblName() + ";");

            while(rs.next()) {
                AttractionClass new_attraction = this.readQueryResult(rs);
                allList.add(new_attraction);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }

        return allList;
    }

    @Override
    public AttractionClass getById(int id) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select "+ AttractionClass.sqlFields() + " from attractions where id = " + id + ";");

            while(rs.next()) {
                return this.readQueryResult(rs);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
        return null;
    }

    @Override
    public void add(AttractionClass new_attraction) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("insert into attractions (" + AttractionClass.sqlFields() + ") values (" +  new_attraction.toSQL() + ");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }

    @Override
    public void edit(AttractionClass attraction) {
        Connection connexion = DBManager.getInstance().getConnection();
        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("update attractions set name = '"+ attraction.getName() +"', type = '"+attraction.getType()+"', sizealone = "+attraction.getSizeAlone()+", \" \r\n"
                    + "    sizewithadult = " + attraction.getSizeWithAdult() + ", \" +\r\n"
                    + "    moOP = '" + String.format("%02d:%02d", attraction.getSchedule()[0].getOpening().getH(), attraction.getSchedule()[0].getOpening().getM()) + "', +\r\n"
                    + "    moCL = '" + String.format("%02d:%02d", attraction.getSchedule()[0].getEnding().getH(), attraction.getSchedule()[0].getEnding().getM()) + "', +\r\n"
                    + "    tuOP = '" + String.format("%02d:%02d", attraction.getSchedule()[1].getOpening().getH(), attraction.getSchedule()[1].getOpening().getM()) + "', +\r\n"
                    + "    tuCL = '" + String.format("%02d:%02d", attraction.getSchedule()[1].getEnding().getH(), attraction.getSchedule()[1].getEnding().getM()) + "', +\r\n"
                    + "    weOP = '" + String.format("%02d:%02d", attraction.getSchedule()[2].getOpening().getH(), attraction.getSchedule()[2].getOpening().getM()) + "', +\r\n"
                    + "    weCL = '" + String.format("%02d:%02d", attraction.getSchedule()[2].getEnding().getH(), attraction.getSchedule()[2].getEnding().getM()) + "', +\r\n"
                    + "    thOP = '" + String.format("%02d:%02d", attraction.getSchedule()[3].getOpening().getH(), attraction.getSchedule()[3].getOpening().getM()) + "', +\r\n"
                    + "    thCL = '" + String.format("%02d:%02d", attraction.getSchedule()[3].getEnding().getH(), attraction.getSchedule()[3].getEnding().getM()) + "', +\r\n"
                    + "    frOP = '" + String.format("%02d:%02d", attraction.getSchedule()[4].getOpening().getH(), attraction.getSchedule()[4].getOpening().getM()) + "', +\r\n"
                    + "    frCL = '" + String.format("%02d:%02d", attraction.getSchedule()[4].getEnding().getH(), attraction.getSchedule()[4].getEnding().getM()) + "', +\r\n"
                    + "    saOP = '" + String.format("%02d:%02d", attraction.getSchedule()[5].getOpening().getH(), attraction.getSchedule()[5].getOpening().getM()) + "', +\r\n"
                    + "    saCL = '" + String.format("%02d:%02d", attraction.getSchedule()[5].getEnding().getH(), attraction.getSchedule()[5].getEnding().getM()) + "', +\r\n"
                    + "    suOP = '" + String.format("%02d:%02d", attraction.getSchedule()[6].getOpening().getH(), attraction.getSchedule()[6].getOpening().getM()) + "', +\r\n"
                    + "    suCL = '" + String.format("%02d:%02d", attraction.getSchedule()[6].getEnding().getH(), attraction.getSchedule()[6].getEnding().getM()) + "' +\r\n"
                    + "    WHERE id = " + attraction.getID() + ";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }
}
