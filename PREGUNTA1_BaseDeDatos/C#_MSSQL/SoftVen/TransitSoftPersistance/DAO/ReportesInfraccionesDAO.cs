using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface ReportesInfraccionesDAO
    {
        int Insertar(ReportesInfraccionesDTO reporte);
        ReportesInfraccionesDTO ObtenerPorId(int reporteId);
        BindingList<ReportesInfraccionesDTO> ListarTodos();
        int Modificar(ReportesInfraccionesDTO reporte);
        int Eliminar(ReportesInfraccionesDTO reporte);
    }

}
