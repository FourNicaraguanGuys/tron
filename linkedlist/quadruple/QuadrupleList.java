package linkedlist.quadruple;

public class QuadrupleList<T> {
	
	private QuadrupleNode<T> origin;
	private int rows;
	private int columns;

	/** Constructor de la lista cuadruple recibe como parametro
	 * el numero de filas y columnas que desea crear 
	 * 
	 * @param numberOfRows
	 * @param numberOfColumns
	 */
	public QuadrupleList(int numberOfRows, int numberOfColumns) {
		if (numberOfRows > 0 && numberOfColumns > 0) {
			this.setRows(numberOfRows);
			this.columns = numberOfColumns;
			this.origin = createRow(numberOfColumns);	
			QuadrupleNode<T> topRow = origin;
			QuadrupleNode<T> botRow = createRow(numberOfColumns);
			for(int counter = 1; counter < numberOfRows; counter++) {
				connectRows(numberOfColumns,topRow,botRow);
				topRow = botRow;
				botRow = createRow(numberOfColumns);
			}
		}
	}
	/** Crea una fila de la matriz dependiendo
	 * de la cantidad de elementos que se le pidan. 
	 * @param numberOfElements
	 * 
	 */
	private QuadrupleNode<T> createRow(int numberOfElements) {
		QuadrupleNode<T> head = new QuadrupleNode<T>();
		QuadrupleNode<T> index = head;
		for (int counter = 1; counter < numberOfElements; counter++) {
			QuadrupleNode<T> temporalNode = new QuadrupleNode<T>();
			temporalNode.setWestNode(index);
			index.setEastNode(temporalNode);
			index = temporalNode;
		}
		return head;
	}
	/** Crea una fila de la matriz dependiendo
	 * de la cantidad de elementos que se le pidan. 
	 * 
	 * @param numberOfColumns
	 * @param topRow
	 * @param botRow
	 * 
	 */
	private void connectRows(int numberOfColumns, QuadrupleNode<T> topRow, QuadrupleNode<T> botRow) {
		QuadrupleNode<T> topNode = topRow;
		QuadrupleNode<T> botNode = botRow;
		for (int index = 0; index < numberOfColumns; index++) {
			topNode.setSouthNode(botNode);
			botNode.setNorthNode(topNode);
			topNode = topNode.getEastNode();
			botNode = botNode.getEastNode();
		}
	}
	
	/** Metodo que obtiene una posicion de la matriz y le cambia 
	 * la informacion que contiene
	 * 
	 * @param data
	 * @param rowPosition
	 * @param columnPosition
	 */
	public void setNodeData(T data, int rowPosition, int columnPosition) {
		if (origin != null) {
			QuadrupleNode<T> pointer = origin;
			for (int rowIndex = 1; rowIndex < rowPosition; rowIndex++) {
				pointer = pointer.getSouthNode();
			}
			for (int columnIndex = 1; columnIndex < columnPosition; columnIndex++) {
				pointer = pointer.getEastNode();
			}
			pointer.setData(data);
		}
	}

	/** Metod que obtiene la el nodo de un fila y columna de a matriz
	 * especificada en los argumentos 
	 * 
	 * @param rowPosition
	 * @param columnPosition
	 * @return QuadrupleNode<T>
	 */
	public QuadrupleNode<T> getNode(int rowPosition, int columnPosition) {
		QuadrupleNode<T> pointer = null;
		if (origin != null) {
			pointer = origin;
			for (int rowIndex = 1; rowIndex < rowPosition; rowIndex++) {
				pointer = pointer.getSouthNode();
			}
			for (int columnIndex = 1; columnIndex < columnPosition; columnIndex++) {
				pointer = pointer.getEastNode();
			}
		}
		return pointer;
	}
	
	/**Obtiene el origen de una lista, es decir la posicion (1,1)
	 * 
	 * @return QuadrupleNode<T>
	 */
	public QuadrupleNode<T> getOrigin() {
		return origin;
	}
	
	/** This method generates a matrix array of objects 
	 * filled with the data of each of the quadruple list node
	 * 
	 * @return Object[][]
	 */
	public Object[][] generateMatrix() {
		Object[][] matrixArray = new Object[columns][rows];
		QuadrupleNode<T> pointer = origin;
		int column = 1, row = 1;
		while(pointer != null) {
			if(pointer.getEastNode() != null) {
				matrixArray[row-1][column-1] = getNode(row,column).getData();
				column++;
				pointer = pointer.getEastNode();
			}
			else {
				matrixArray[row-1][column-1] = getNode(row,column).getData();
				column = 1;
				row++;
				pointer = getNode(row,column);
			}
		}
		return matrixArray;
	}
	
	
	/** Metodo que define el origen de una matriz, prefireblemente
	 * la posicion (1,1)
	 * 
	 * @param origin
	 */
	public void setOrigin(QuadrupleNode<T> origin) {
		this.origin = origin;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
}


