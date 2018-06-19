public class DFSCell extends Cell{
  private DFSCell parent;

  public void setParent(DFSCell parent) {
    this.parent = parent;
  }

  public DFSCell getParent() {
    return parent;
  }

  public static DFSCell convertFromCell(Cell cell, DFSCell parent) {
    int[] position = cell.getCellPos();
    DFSCell result = new DFSCell(position[0], position[1]);
    result.setParent(parent);
    return result;
  }

  public Cell convertToCell() {
    return new Cell(this.y, this.x);
  }
}
