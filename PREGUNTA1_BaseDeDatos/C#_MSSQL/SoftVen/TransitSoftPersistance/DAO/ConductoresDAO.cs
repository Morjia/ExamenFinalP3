using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface ConductoresDAO
    {
        int Insertar(ConductoresDTO conductor);
        ConductoresDTO ObtenerPorId(int conductorId);
        BindingList<ConductoresDTO> ListarTodos();
        int Modificar(ConductoresDTO conductor);
        int Eliminar(ConductoresDTO conductor);
    }
}
