using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface VehiculosDAO
    {
        int Insertar(VehiculosDTO vehiculo);
        VehiculosDTO ObtenerPorId(int vehiculoId);
        BindingList<VehiculosDTO> ListarTodos();
        int Modificar(VehiculosDTO vehiculo);
        int Eliminar(VehiculosDTO vehiculo);
    }
}
