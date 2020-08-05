package tk.kingalteriv.mesystem.utilities.enums;

public enum MEItemType {
    METerminal,
    MEDrive,
    MECell,
    Null;

    public static MEItemType fromString(String type){
        switch (type) {
            case "METerminal":
                return MEItemType.METerminal;
            case "MEDrive":
                return MEItemType.MEDrive;
            case "MECell":
                return MEItemType.MECell;
            default:
                return Null;
        }
    }


}
